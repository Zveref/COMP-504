'use strict';

//app to draw polymorphic shapes on canvas
let app;

//id of current interval
let intervalID;

/**
 * Draw and clear line on a the canvas
 * @param canvas  The canvas used to draw a line
 * @returns {{drawLine: drawLine, clear: clear}}
 */
function createApp(canvas) {
    let c = canvas.getContext("2d");

    let drawLine = function(startX, startY, endX, endY) {
        var randomColor = Math.floor(Math.random()*16777215).toString(16);
        c.strokeStyle = "#" + randomColor;

        if(endX <= 800){
            c.beginPath();
            c.moveTo(startX, startY);
            c.lineTo(endX, endY);
            c.stroke();
        }else if(endX > 800 && startX <= 800){
            c.beginPath();
            c.moveTo(startX, startY);
            c.lineTo(800, (800 - startX) * 0.5);
            c.stroke();
        }

    };

    let clear = function() {
        c.clearRect(0,0, canvas.width, canvas.height);
    };

    return {
        drawLine: drawLine,
        clear: clear
    }
}

/**
 * Setup event handling for buttons
 */
window.onload = function() {
    app = createApp(document.querySelector("canvas"));

    $("#btn-line").click(createLine);
    $("#btn-move").click(setUpdateFreq);
    $("#btn-reset").click(reset);
    $("#btn-stop").click(stopDrawing);
};

/**
 * Create a line at a location on the canvas
 */
var clicktimes = 0;
function createLine() {
    $.get("/line", function (data) {
        console.log("data is " + data);
       // TODO: draw a line that does not move
        if(clicktimes == 0){
            var x1 = data.startX;
            var y1 = data.startY;
            var x2 = data.endX;
            var y2 = data.endY;
            app.drawLine(x1, y1, x2, y2);
            clicktimes++;
        }else{
            alert("You've already drawn one!");
        }

    }, "json");
}

/**
 * Determine how often line updates occur
 */
function setUpdateFreq() {
    // TODO: move the line every .2 seconds by setting an interval

    if(clicktimes == 0){
        alert("You should draw one first");
    }else{
        intervalID = setInterval(updateLine, 200);
    }
}

/**
 * Update a line on the canvas
 */
function updateLine() {
    $.get("/update", function (data) {
        console.log("data is " + data);
       // TODO: update the line position
            var x1 = data.startX;
            var y1 = data.startY;
            var x2 = data.endX;
            var y2 = data.endY;
            app.drawLine(x1, y1, x2, y2);
    }, "json");
}

/**
 * Reset canvas
 */
function reset() {
    $.get("/reset", function (data) {
        // TODO: reset the canvas, no line should appear
        clear();
        clicktimes = 0;
        document.getElementById("reminder").innerHTML ="";
    }, "json");

}

function stopDrawing(){
    clearInterval(intervalID);
}

/**
 * Clear the canvas
 */
function clear() {
    app.clear();
}