'use strict';

//app to draw polymorphic shapes on canvas
var app;

/**
 * Create an app that can draw shapes on a canvas. It also has a function to clear the canvas.
 * @param canvas  The canvas to draw shapes on.
 * @returns {{drawCircle: drawCircle, clear: clear}}, an object with functions to draw shapes and clear the canvas.
 */
function createApp(canvas) {
    var c = canvas.getContext("2d");

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
        drawCircle: drawCircle,
        clear: clear
    }
}

/**
 * When the window loads, get the app that can draw shapes on the canvas and setup the button click actions.
 */
window.onload = function() {
    app = createApp(document.querySelector("canvas"));

    $("#btn-circle").click(createCircle);
    $("#btn-clear").click(clear);
};

/**
 * Create a circle at a location on the canvas
 */
function createCircle() {
    $.get("/shape/circle", function (data) {
        console.log("data is " + data);
        // TODO: expect something like this for circle - app.drawCircle(data.loc.x, data.loc.y, data.radius, data.color);
        app.drawCircle(100, 100, 25, "red");
    }, "json");
}

/**
 * Clear the canvas
 */
function clear() {
    app.clear();
}