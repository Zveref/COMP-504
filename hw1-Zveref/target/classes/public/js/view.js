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

    let drawTriangle = function(x, y, size, color) {
        c.fillStyle = color;
        c.beginPath();
        c.moveTo (x + size * Math.cos(0), y + size * Math.sin(0));

        for (let i = 1; i <= 3;i += 1) {
            c.lineTo (x + size * Math.cos(i * 2 * Math.PI / 3), y + size * Math.sin(i * 2 * Math.PI / 3));
        }
        c.closePath();
        c.fill();
    };

    let drawSquare = function(x, y, size, color) {
        c.fillStyle = color;
        c.beginPath();
        c.rect(x, y, size, size);
        c.closePath();
        c.fill();
    };

    let drawHexagon = function(x, y, size, color) {
        c.fillStyle = color;
        c.beginPath();
        c.moveTo (x + size * Math.cos(0), y + size * Math.sin(0));

        for (let i = 0; i <= 5; i += 1) {
            c.lineTo (x + size * Math.cos(i * Math.PI / 3), y + size * Math.sin(i * Math.PI / 3));
        }
        c.closePath();
        c.fill();
    };

    let drawClub = function(x, y, radium, color){
        c.fillStyle = color;
        c.beginPath();
        c.arc(x, y - 2 * radium, radium, 0, 2 * Math.PI, true);
        c.moveTo(x - radium * Math.sin(2 * Math.PI / 3), y + radium * Math.cos(2 * Math.PI / 3));
        c.arc(x - radium * Math.sin(2 * Math.PI / 3), y + radium * Math.cos(2 * Math.PI / 3), radium, 0, 2 * Math.PI, true);
        c.moveTo(x + radium * Math.sin(2 * Math.PI / 3), y + radium * Math.cos(2 * Math.PI / 3));
        c.arc(x + radium * Math.sin(2 * Math.PI / 3), y + radium * Math.cos(2 * Math.PI / 3), radium, 0, 2 * Math.PI, true);
        c.closePath();
        c.fill();
    }


    let clear = function() {
        c.clearRect(0,0, canvas.width, canvas.height);
    };

    return {
        drawCircle: drawCircle,
        drawTriangle: drawTriangle,
        drawSquare: drawSquare,
        drawHexagon: drawHexagon,
        drawClub: drawClub,
        clear: clear,
        dims: {height: canvas.height, width: canvas.width}
    }
}

/**
 * When the window loads, get the app that can draw shapes on the canvas and setup the button click actions.
 */
window.onload = function() {
    /**
     * Pass along the canvas dimensions
     */
    function canvasDims() {
        $.post("/canvas/dims", {height: app.dims.height, width: app.dims.width});
    }

    app = createApp(document.querySelector("canvas"));

    $("#btn-circle").click(createCircle);
    $("#btn-club").click(createClub);
    $("#btn-compositeshape").click(createCompositeShape);
    $("#btn-hexagon").click(createHexagon);
    $("#btn-triangle").click(createTriangle);
    $("#btn-square").click(createSquare);
    $("#btn-clear").click(clear);

};




/**
 * Create a circle at a location on the canvas
 */
function createCircle() {
    $.get("/shape/circle", function (data) {
        console.log("data is " + data);
        for (var i=0; i<data.shapes.length; i++){
            app.drawCircle(data.shapes[i].Loc.x, data.shapes[i].Loc.y, data.shapes[i].Radius, data.shapes[i].Color);
        }
    }, "json");
}


/**
 * Create a club at a location on the canvas
 */
function createClub() {
    $.get("/shape/club", function (data) {
        console.log("data is " + data);
        for (var i=0; i<data.shapes.length; i++){
            app.drawClub(data.shapes[i].Loc.x, data.shapes[i].Loc.y, data.shapes[i].Radius, data.shapes[i].Color);
        }
    }, "json");
}

/**
 * Create a compositeshape at a location on the canvas
 */
function createCompositeShape() {
    $.get("/shape/compositeshape", function (data) {
        console.log("data is " + data);
        for (var i=0;i<data.DoubleShapes.length;i++)
        {
            var shape1 = data.DoubleShapes[i].DoubleShape[0];
            var shape2 = data.DoubleShapes[i].DoubleShape[1];
            if(shape1.myclass == "Circle") {
                app.drawCircle(shape1.Loc.x, shape1.Loc.y, shape1.Radius, shape1.Color);
            }else if(shape1.myclass == "Club"){
                app.drawClub(shape1.Loc.x, shape1.Loc.y, shape1.Radius, shape1.Color);
            }else if(shape1.myclass == "Hexagon"){
                app.drawHexagon(shape1.Loc.x, shape1.Loc.y, shape1.Size, shape1.Color);
            }else if(shape1.myclass == "Square"){
                app.drawSquare(shape1.Loc.x, shape1.Loc.y, shape1.Size, shape1.Color);
            }else{
                app.drawTriangle(shape1.Loc.x, shape1.Loc.y, shape1.Size, shape1.Color);
            }
            if(shape2.myclass == "Circle"){
                app.drawCircle(shape2.Loc.x, shape2.Loc.y, shape2.Radius, shape2.Color);
            }else if(shape2.myclass == "Club"){
                app.drawClub(shape2.Loc.x, shape2.Loc.y, shape2.Radius, shape2.Color);
            }else if(shape2.myclass == "Hexagon"){
                app.drawHexagon(shape2.Loc.x, shape2.Loc.y, shape2.Size, shape2.Color);
            }else if(shape2.myclass == "Square"){
                app.drawSquare(shape2.Loc.x, shape2.Loc.y, shape2.Size, shape2.Color);
            }else{
                app.drawTriangle(shape2.Loc.x, shape2.Loc.y, shape2.Size, shape2.Color);
            }
        }
    }, "json");
}

/**
 * Create a pentagon at a location on the canvas
 */
function createHexagon() {
    $.get("/shape/hexagon", function (data) {
        console.log("data is " + data);
        for (var i=0;i<data.shapes.length;i++){
            app.drawHexagon(data.shapes[i].Loc.x, data.shapes[i].Loc.y, data.shapes[i].Size, data.shapes[i].Color);
        }
    }, "json");
}


/**
 * Create a square at a location on the canvas
 */
function createSquare() {
    $.get("/shape/square", function (data) {
        console.log("data is " + data);
        for (var i=0;i<data.shapes.length;i++){
            app.drawSquare(data.shapes[i].Loc.x, data.shapes[i].Loc.y, data.shapes[i].Size, data.shapes[i].Color);
        }
    }, "json");
}


/**
 * Create a triangle at a location on the canvas
 */
function createTriangle() {
    $.get("/shape/triangle", function (data) {
        console.log("data is " + data);
        for (var i=0;i<data.shapes.length;i++){
            app.drawTriangle(data.shapes[i].Loc.x, data.shapes[i].Loc.y, data.shapes[i].Size, data.shapes[i].Color);
        }
    }, "json");
}



/**
 * Clear the canvas
 */
function clear() {
    $.get("/clear");
      app.clear();
}

