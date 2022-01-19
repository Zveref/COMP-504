'use strict'

var app;

function initialApp() {

    const gameBoard = document.getElementById("game-board")
    const c = gameBoard.getContext("2d");
    c.fillStyle = '#000000';
    c.fill();
    

    const drawImage = function (x, y, size, source) {
        c.save();
        c.drawImage(source, x, y, size, size);
        c.restore();
    };

    const drawLine = function(x, y, size, color) {
        // c.fillStyle = color;
        // c.fillRect(x, y, size, size)
        c.strokeStyle = color;
        c.lineWidth = 4;
        c.strokeRect(x, y, size, size);
    };

    const drawEyes = function(x, y, size, color) {
        c.fillStyle = color;
        c.fillRect(x, y, size, size)
    };

    const drawCircle = function(x, y, size, color) {
        c.beginPath();
        c.arc(x + 10, y + 10, size / 4, 0, 2 * Math.PI, false);
        c.closePath();
        c.fillStyle = color;
        c.fill();
    };

    const clear = function() {
        c.clearRect(0, 0, gameBoard.width, gameBoard.height);
        c.fillRect(0, 0, gameBoard.width, gameBoard.height);
        c.fillStyle = "black";
    };

    return {
        drawImage: drawImage,
        drawCircle: drawCircle,
        drawLine: drawLine,
        drawEyes: drawEyes,
        clear: clear,
    }
}

const map = [
    [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0],
    [1, 1, 2, 1, 1, 0, 0, 1, 1, 1, 1, 1,10, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0],
    [0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0],
    [0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0],
    [0, 1, 0,10, 1, 1, 1, 1, 1, 1, 1, 0, 1,18,18, 1,18,18, 1, 0, 1, 0, 0, 1, 0, 1, 1,10, 1, 0, 1, 0],
    [0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0],
    [0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 4, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0],
    [0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 5, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0],
    [0, 1, 0, 0, 0, 1, 0, 0, 1, 1,10, 1, 0, 1, 0, 6, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0],
    [0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 7, 1, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1,10, 0],
    [0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0],
    [0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0],
    [0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0],
    [0, 1, 0, 1,10, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 9, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0],
    [0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0],
    [0, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0],
    [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
];

window.onload = function() {
    app = initialApp();
    initialGameWorld()
}

function initialGameWorld() {

    app.clear();

    const gridSize = 40

    for (let x = 0; x < map.length; x++) {
        for (let y = 0; y < map[0].length; y++) {
            //small dot
            if (map[x][y] === 1) {
                app.drawCircle(y * gridSize + 10, x * gridSize + 10, gridSize / 2, "#650d4a");
            }

            //large dot
            if (map[x][y] === 2) {
                app.drawCircle(y * gridSize + 10, x * gridSize + 10, gridSize, "#650d4a");
            }

            if (map[x][y] === 4) {
                app.drawImage( y * gridSize, x * gridSize, gridSize, document.getElementById('redGhost'));
            }

            if (map[x][y] === 5) {
                app.drawImage( y * gridSize, x * gridSize, gridSize, document.getElementById('pinkGhost'));
            }

            if (map[x][y] === 6) {
                app.drawImage( y * gridSize, x * gridSize, gridSize, document.getElementById('greenGhost'));
            }

            if (map[x][y] === 7) {
                app.drawImage( y * gridSize, x * gridSize, gridSize, document.getElementById('whiteGhost'));
            }

            //wall--green
            if (map[x][y] === 8) {
                app.drawLine(y * gridSize, x * gridSize, gridSize, "#00cc00");
            }

            //wall--red
            if (map[x][y] === 18) {
                app.drawLine(y * gridSize, x * gridSize, gridSize, "#7c0200");
            }

            //wall--blue
            if (map[x][y] === 0) {
                app.drawLine(y * gridSize, x * gridSize, gridSize, "#F5F0F0");
            }

            if (map[x][y] === 9) {
                app.drawImage( y * gridSize, x * gridSize, gridSize, document.getElementById('pcman'));
            }

            if (map[x][y] === 10) {
                app.drawImage(y * gridSize, x * gridSize, gridSize, document.getElementById('bone'));
            }


        }
    }

}