'use strict';

//app to draw polymorphic shapes on canvas
var app;
let intervalID = -1;

/**
 * Create the ball world app for a canvas
 * @param canvas The canvas to draw balls on
 * @returns {{drawBall: drawBall, clear: clear}}
 */
function createApp(canvas) {
    let c = canvas.getContext("2d");

    /**
     * Draw a circle
     * @param x  The x location coordinate
     * @param y  The y location coordinate
     * @param radius  The circle radius
     * @param color The circle color
     */
    let drawCircle = function(x, y, radius, color) {
        c.fillStyle = color;
        c.beginPath();
        c.arc(x, y, radius, 0, 2 * Math.PI, false);
        c.closePath();
        c.fill();
    };

    let clear = function() {
        c.clearRect(0,0, canvas.width, canvas.height);
    };


    return {
        drawCircle,
        clear,
        dims: {height: canvas.height, width: canvas.width}
    }
}


window.onload = function() {
    app = createApp(document.querySelector("canvas"));

    // function canvasDims() {
    //     $.post("/canvas/dims", {height: app.dims.height, width: app.dims.width});
    // }

    canvasDims();

    $("#btn-composite").click(function() {loadBall("composite")});
    $("#btn-horizontal").click(function() {loadBall("horizontal")});
    $("#btn-linger").click(function() {loadBall("linger")});
    $("#btn-rotate").click(function() {loadBall("rotate")});
    $("#btn-shaking").click(function() {loadBall("shaking")});
    $("#btn-vertical").click(function() {loadBall("vertical")});

    $("#btn-clear").click(clear);
};

/**
 * load ball at a location on the canvas
 */
function loadBall(kind) {
    $.post("/load/" + kind,  function (data) {
        console.log("data is " + data);
        drawBall(data);
        if(intervalID < 0){
            intervalID = setInterval(updateBallWorld, 100);
        }
    }, "json");
}

/**
 * Switch ball strategies
 */
function switchStrategy() {
    let values = "";
    $.post("/switch", { strategies: values}, function (data) {

    }, "json");
}

function updateBallWorld() {
    $.get("/update", function(data) {
        console.log("data is " + data);
        app.clear();
        drawBall(data);
    }, "json");
}

function drawBall(data) {
        for (var i=0; i<data.length; i++){
            var shape = data[i]
            app.drawCircle(shape.loc.x, shape.loc.y, shape.radius, shape.color);
        }
    }


/**
 * Pass along the canvas dimensions
 */
function canvasDims() {
    $.post("/canvas/dims", {height: app.dims.height, width: app.dims.width});
}

/**
 * Clear the canvas
 */
function clear() {
    $.get("/clear");
    app.clear();
    clearInterval(intervalID);
    intervalID = -1;
}