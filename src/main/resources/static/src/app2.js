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

/*function cambiaImmagine(cameriere) {
    privateStompClient.send("/app/cambia", {}, JSON.stringify({'cameriere': cameriere}));
}*/

function show(message) {
    var response;
    if(message.nomeTavolo != null) {
        response = document.getElementById('tabella');
        var row = response.insertRow(0);
        row.id= message.nomeTavolo +"--"+message.cameriere;
        let persone=row.insertCell(0);
        let tavolo=row.insertCell(1);
        let nome=row.insertCell(2);
        let piatti=row.insertCell(3);
        persone.style.width="20%";
        persone.innerHTML=message.persone;
        tavolo.style.width="20%";
        tavolo.innerHTML=message.numeroTavolo;
        nome.style.width="20%";
        nome.innerHTML=message.nomeTavolo;
        piatti.style.width="40%";
        piatti.innerHTML="<table id='piatti'></table>";
        fill(message);
    }/*else{
        document.getElementById(message.cameriere+message.nomeTavolo).src=message.value;
    }*/
}