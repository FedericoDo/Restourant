var stompClient = null;

$(document).ready(function() {
    console.log("Index page is ready");
    connect();

    //
    // $("#send").click(function() {
    //     sendMessage();
    // });
    //
    // $("#send-private").click(function() {
    //     sendPrivateMessage();
    // });
});

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        // stompClient.subscribe('/topic/messages', function (message) {
        //     showMessage(JSON.parse(message.body).content);
        // });

        stompClient.subscribe('/user/topic/private-messages', function (message) {
           // showMessage(JSON.parse(message.body).content);
           showMessage(message.body);
        });
    });
}

function showMessage(message) {
    console.log("arrivato");
    $("#messages").append("<tr><td>" + message + "</td></tr>");
}

function sendMessage() {
    var text = document.getElementById('privateText').value;
    var to = document.getElementById('to').value;
    console.log("sending message");
    stompClient.send("/ws/message", {}, JSON.stringify({'text': text, "to":to}));
}

// function sendPrivateMessage() {
//     console.log("sending private message");
//     stompClient.send("/ws/private-message", {}, JSON.stringify({'messageContent': $("#private-message").val()}));
// }

