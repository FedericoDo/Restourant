var privateStompClient = null;

var socket;

socket = new SockJS('/ws');
privateStompClient = Stomp.over(socket);
privateStompClient.connect({}, function(frame) {
    console.log(frame);
    privateStompClient.subscribe('/user/specific', function(result) {
        console.log(result.body)
        show(JSON.parse(result.body));
    });
});


function sendPrivateMessage() {
    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;

    privateStompClient.send("/app/registra", {},
        JSON.stringify({'username':username,'password':password}));
}

function show(message) {
    var response = document.getElementById('messages');
    response.innerHTML = message.valore;
}