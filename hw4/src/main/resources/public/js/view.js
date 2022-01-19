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

    let drawFish = function(x, y, radius, direction, needflip) {
        var image = new Image();
        var spX = direction.x;
        var spY = direction.y;
        var degree = Math.atan(spY / spX);
        if(spX >= 0){
            image.src = "fishR.png";
        }else if(spX < 0 && spY < 0){
            degree = - Math.atan(- spY / spX);
            image.src = "fishL.png";
        }else if(spX < 0 && spY >= 0) {
            degree = Math.atan(spY / spX);
            image.src = "fishL.png"

        }
        // }else if(spX == 0 && spY < 0){
        //     degree = Math.PI / 2;
        //     image.src = "fishL.png";
        // }else if(spX == 0 && spY > 0){
        //     degree = Math.PI / 2;
        //     image.src = "fishR.png";
        // }
        if(needflip && y < canvas.height / 2){
            image.src = "fishR.png"
            degree = 0;
        }

        c.save() ;
        c.translate(x , y );
        c.rotate(degree);
        c.drawImage(image, -radius, -radius, 2 * radius, 2 *radius) ;
        c.restore() ;
        //drawImage(image, x - radius, y - radius, 2 * radius, 2 *  radius);
    }


    return {
        drawFish,
        drawCircle,
        clear,
        dims: {height: canvas.height, width: canvas.width}
    }
}



window.onload = function() {
    app = createApp(document.querySelector("canvas"));


    canvasDims();

    $("#btn-submitCreate").click(readCreate);
    $("#btn-submitRemove").click(readRemove);
    $("#btn-clearAll").click(clearAll);
    $("#btn-switch").click(switchStrategy);


};

function readCreate(){
    var Strategy = document.getElementById("createStrategy")
    var Type = document.getElementById("createType")
    var Character = document.getElementById("createCharacter")
    var canSwitch = document.getElementById("switchable").checked
    var index1 = Strategy.selectedIndex;
    var MyStrategy = Strategy.options[index1].value;

    var index2 = Type.selectedIndex;
    var MyType  = Type.options[index2].value

    var index5 = Character.selectedIndex;
    var MyCharacter = Character.options[index5].value;




    if(MyStrategy == "default" || MyType == "default"){
        alert("Choose what you want to create")
    }
    loadObj(MyStrategy, MyType, canSwitch, MyCharacter);
}



function readRemove(){
    var TypeChoice = document.getElementById("removeType");
    var index4 = TypeChoice.selectedIndex;
    var RemoveType = TypeChoice.options[index4].value;

    var StrategyChoice = document.getElementById("removeStrategy");
    var index3 = StrategyChoice.selectedIndex;
    var RemoveStrategy  = StrategyChoice.options[index3].value;
    if(RemoveStrategy == "default" || RemoveType == "default"){
        alert("type compulsory")
    }
    remove(RemoveStrategy, RemoveType);
}


// function readSwitch(){
//     var SwitchChoice = document.getElementById("switchStrategy")
//     var index3 = SwitchChoice.selectedIndex;
//     var MySwitch = SwitchChoice.options[index3].value;
//     if(MySwitch == "default"){
//         alert("What you want to switch?")
//     }
//     switchStrategy(MySwitch);
// }



/**
 * load ball at a location on the canvas
 */
function loadObj(strategy, type, swichable, character) {
    $.post("/load/" + strategy + "/" + type + "/" + character + "/" + swichable,  function (data) {
        console.log("data is " + data);
        if(type == "ball"){
            app.drawCircle(data.loc.x, data.loc.y, data.radius, data.color);
        }else{

            app.drawFish(data.loc.x, data.loc.y, data.radius, data.direction, data.needflip);
        }
        if(intervalID < 0){
            intervalID = setInterval(updateObj, 100);
        }
    }, "json");
}


/**
 * Switch ball strategies
 */
function switchStrategy() {
    let values = "switch strategy";
    $.post("/switch", { SwitchChoice: values}, function (data) {

    }, "json");
}

function updateObj() {
    $.get("/update", function(data) {
        console.log("data is " + data);
        app.clear();
        data.forEach(function(po) {
            if (po.listener.type === "ball")
                app.drawCircle(po.listener.loc.x, po.listener.loc.y, po.listener.radius, po.listener.color);
        });

        data.forEach(function(po) {
            if (po.listener.type === "fish")
                app.drawFish(po.listener.loc.x, po.listener.loc.y, po.listener.radius , po.listener.direction, po.listener.needflip);
        });


    }, "json");
}



/**
 * Pass along the canvas dimensions
 */
function canvasDims() {
    $.post("/canvas/dims", {height: app.dims.height, width: app.dims.width});
}

function remove(strategy, choice) {
    $.get("/remove/" + strategy + "/" + choice);
    app.clear();

}




/**
 * Clear the canvas
 */
function clearAll() {
    $.get("/clear");
    app.clear();
    clearInterval(intervalID);
    intervalID = -1;
}
