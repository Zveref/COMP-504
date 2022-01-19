'use strict';

let webSocket = null;

if (window.localStorage.getItem('isLogin') !== 'true') {
    window.location.href = './index.html'
}
$(function () {

    $(".chat").niceScroll();
    $("#inputMsg").emojioneArea({
        pickerPosition: "bottom",
        tonesStyle: "radio"
    });

});

let createModal = $("#create-modal")[0];
let notifyModal = $("#notif-modal")[0];
let profileModal = $("#profile-modal")[0];

$("#plus-btn").click(() => {
    createModal.style.display = "block";
});

$("#notification-btn").click(() => {
    notifyModal.style.display = "block";
});

$("#profile-btn").click(() => {

    $('#username').val(window.localStorage.getItem('username'));
    $('#school').val(window.localStorage.getItem('school'));
    $('#interest').val(window.localStorage.getItem('interest'));
    $('#DOB').val(window.localStorage.getItem('birthDate'));


    profileModal.style.display = "block";
});

$("#logout-btn").click(() => {
    window.localStorage.removeItem('username');
    window.localStorage.removeItem('school');
    window.localStorage.removeItem('interest');
    window.localStorage.removeItem('birthDate');
    window.localStorage.setItem('isLogin', 'false')
    window.location.href = './index.html'
})

window.onload = function() {
    let userId = window.localStorage.getItem("userId");
    // let channel = getJoinedChannels(userId);
    // loadJoinedChannels();

}

function getJoinedChannels(userId) {
    $.get("joinedRooms/" + userId, function(data){
        return data
    })
}

function loadJoinedChannels() {


}

$("#quit-create-chatroom").click(() => {
    createModal.style.display = "none";
})

$("#leave-room").click(() => {

    const leaveRoomValues = {
        userId: 1,
        channelId: 5
    }

    $.post("/leaveChannel", leaveRoomValues, function (data) {
        // console.log(data)
    });
})

$("#submit-create-chatroom").click(() => {

        let roomName = $('#RoomName').val()
        let capacity = $('#RoomCapacity').val()
        let isPrivate =  document.getElementById("ifPrivate").checked === false
        if (checkIsValid(roomName) && checkIsValid(capacity)) {
            const createChannelValues = {
                adminId: window.localStorage.getItem('userId'),
                channelName: roomName,
                capacity: capacity,
                isPrivate: isPrivate,
            }

            $.post("/createChannel", createChannelValues, function (data) {

                const dataParse = JSON.parse(data)



                createModal.style.display = "none";
                $('#chat-header-id').empty();
                $('#chat-header-id').append(
                    `<h6>${dataParse.channelName}</h6>` +
                    `<p style="padding: 2px">Room Capacity: ${dataParse.capacity}</p>`
                )
                // assert chat room
                $("#chat-body-id").css('display', 'block')

                $.get(`/joinedChannels/${window.localStorage.getItem('userId')}`, function (data) {
                    console.log(data)
                    if(data.length > 0) {
                        displayChatRoomList(JSON.parse(data))
                    }
                });
                // console.log(data)
            });
        } else {

            if (!checkIsValid(roomName)) {
                $("#room-name-invalid-feedback").css('display', 'block')
                $("#room-name-invalid-feedback").css('color', 'red')
            }
            if (!checkIsValid(capacity)) {
                $("#room-capacity-invalid-feedback").css('display', 'block')
                $("#room-capacity-invalid-feedback").css('color', 'red')
            }
        }

        if (checkIsValid(roomName)) {
            $("#room-name-invalid-feedback").css('display', 'none')
        }

        if (checkIsValid(capacity)) {
            $("#room-capacity-invalid-feedback").css('display', 'none')
        }

})

function checkIsValid(value) {
    return value === undefined || value === '' ? false : true
}

// When the user clicks anywhere outside of the modal, close it
$(window).click(function (event) {
    if (event.target === createModal || event.target === notifyModal || event.target === profileModal) {
        event.target.style.display = "none";
    }
});


// get chat room list

const chatRoomData = [
    {
        roomId: 1,
        members: 5,
        roomName: 'Music',
        isPrivate: false
    },
    {
        roomId: 2,
        members: 3,
        roomName: 'Sports',
        isPrivate: true
    },
    {
        roomId: 3,
        members: 23,
        roomName: 'Life In Houston',
        isPrivate: false
    },
    {
        roomId: 4,
        members: 23,
        roomName: 'Sports',
        isPrivate: false
    },
    {
        roomId: 5,
        members: 23,
        roomName: 'Sports',
        isPrivate: false
    }
]

function changeChannel(roomId) {
    $.get(`/switchChannel/${roomId}`, function(data){

        const parseData = JSON.parse(data);

        // {admin ..., roommates: [
        //              d.id
        //             d.name
        //             d.blockNum
        //             d.muteStatus
        // ]}
        // admin
        $('#chat-header-id').empty();
        $('#chat-header-id').append(
            `<h6>${parseData.channelName}</h6>` +
            `<p style="padding: 2px">Room Capacity: ${parseData.capacity}</p>`
        )
        displayChannelUserList(parseData.roommates)

    })

}

function displayChatRoomList(data) {
    console.log(data)
    $("#chat-room-list").empty()
    data.forEach((d)=>{

        if (!d.ifLocked) {
            $("#chat-room-list").append(
                `<button class="user" onclick="changeChannel(${d.channelId})">` +
                   ' <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-circle pull-right" viewBox="0 0 16 16">' +
                       ' <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>' +
                       ' <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>' +
                   ' </svg>' +
                    '<div class="avatar">' +
                        '<svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-chat-square" viewBox="0 0 16 16">' +
                            '<path d="M14 1a1 1 0 0 1 1 1v8a1 1 0 0 1-1 1h-2.5a2 2 0 0 0-1.6.8L8 14.333 6.1 11.8a2 2 0 0 0-1.6-.8H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v8a2 2 0 0 0 2 2h2.5a1 1 0 0 1 .8.4l1.9 2.533a1 1 0 0 0 1.6 0l1.9-2.533a1 1 0 0 1 .8-.4H14a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>' +
                        '</svg>' +
                    '</div>' +
                   `<div class="room-name">${d.channelName} <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-lock" viewBox="0 0 16 16">` +
                        '<path d="M8 1a2 2 0 0 1 2 2v4H6V3a2 2 0 0 1 2-2zm3 6V3a3 3 0 0 0-6 0v4a2 2 0 0 0-2 2v5a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2zM5 8h6a1 1 0 0 1 1 1v5a1 1 0 0 1-1 1H5a1 1 0 0 1-1-1V9a1 1 0 0 1 1-1z"/>' +
                   ' </svg>' +
                    '</div>' +
                    '<div class="room-description">Group locked</div>' +
                '</button>'
            )
        } else {
            $("#chat-room-list").append(
                `<button class="user" onclick="changeChannel(${d.channelId})">` +
                '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-circle pull-right" viewBox="0 0 16 16">' +
                ' <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>' +
                '<path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>' +
                '</svg>' +
                ' <div class="avatar">' +
                '<svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-chat-square-dots" viewBox="0 0 16 16" alt="User name">' +
                '<path d="M14 1a1 1 0 0 1 1 1v8a1 1 0 0 1-1 1h-2.5a2 2 0 0 0-1.6.8L8 14.333 6.1 11.8a2 2 0 0 0-1.6-.8H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v8a2 2 0 0 0 2 2h2.5a1 1 0 0 1 .8.4l1.9 2.533a1 1 0 0 0 1.6 0l1.9-2.533a1 1 0 0 1 .8-.4H14a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>' +
                '<path d="M5 6a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>' +
                '</svg>' +
                '</div>' +
                `<div class="room-name">${d.channelName}</div>` +
                `<div class="room-description">Capacity ${d.capacity}</div>` +
                '</button>'
            )
        }


    })

}

function displayChannelUserList(data) {

    $("#channel-user-list").empty()
    data.forEach((d)=>{
        if (d.id === parseInt(window.localStorage.getItem('userId'))) {
            $("#channel-user-list").append(
                '<div class="user">' +
                    '<div class="avatar">' +
                        '<img src="https://bootdey.com/img/Content/avatar/avatar6.png" alt="User name" />' +
                        '<div class="status online"></div>' +
                    '</div>' +
                    '<img class="admin-symbol" src="./images/admin-star.png"/>' +
                    `<div class="name">${d.name}</div>` +
                    '<div class="mood" style="visibility: hidden">consectetur adiping elit</div>' +
                '</div>'
            )
        } else {
            $("#channel-user-list").append(
                '<div class="user">' +
                '<div class="avatar">' +
                ' <img src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="User name" />' +
                '<div class="status off"></div>' +
                '</div>' +
                `<div class="name">${d.name}</div>` +
                '<div class="mood" style="visibility: hidden">consectetur adiping elit</div>' +
                '</div>'
            )
        }


    })

}

$(function () {

    $.get(`/joinedChannels/${window.localStorage.getItem('userId')}`, function (data) {
         console.log(data)
        if(data.length > 0) {
            // displayChatRoomList(chatRoomData)
        }
    });

    // displayChannelUserList(chatRoomData)

});


/**
 * Entry point into chat room
 */
window.onload = function() {
    console.log(window.localStorage.getItem('userId'))
    // local is http should use ws://
    // deploy to heroku should change as wss://
    webSocket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/chatapp?userId=" + window.localStorage.getItem('userId'))

    webSocket.onclose = () => alert("WebSocket connection closed");
    webSocket.onmessage = (msg) => updateChatRoom(msg);

    $("#msgBtn").click(() => sendMessage($("#inputMsg").val()));
};

/**
 * Send a message to the server.
 * @param msg  The message to send to the server.
 */
function sendMessage(msg) {
    if (msg !== "") {
        const msgValues = {
            senderId: '1',
            receiverId: '0',
            channelId: '1',
            data: '2020-02-21',
            content: msg
        }

        webSocket.send(JSON.stringify(msgValues));
        $("#message").val("");
    }
}


/**
 * Update the chat room with a message.
 * @param message  The message to update the chat room with.
 */
function updateChatRoom(message) {
    // TODO: convert the data to JSON and use .append(text) on a html element to append the message to the chat area
    let data = JSON.parse(message.data)
    // console.log(data)

    $("#message-area").append(data)
}
