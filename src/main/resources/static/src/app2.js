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

function changeStatus(className) {
    document.getElementsByClassName(className)[0].style.visibility="hidden";
    if(className.includes("cross")){
        document.getElementsByClassName(className.replace("cross","tic"))[0].style.visibility="visible";
    }else{
        document.getElementsByClassName(className.replace("tic","cross"))[0].style.visibility="visible";
    }
    let cameriere = className.split("_")[1];
    let tavolo = className.split("_")[0];
    privateStompClient.send("/app/cambia", {}, JSON.stringify({'cameriere': cameriere, 'tavolo': tavolo}));
}

function show(message) {
    var response;
    if(message.nomeTavolo != null) {
        response = document.getElementById('tabella');
        var row = response.insertRow(response.rows.length);
        let casella=row.insertCell(0);
        let persone=row.insertCell(1);
        let tavolo=row.insertCell(2);
        let nome=row.insertCell(3);
        let piatti=row.insertCell(4);
        casella.style.width="15%";
        casella.innerHTML='<img src="/database/inCorso.jpg" class="'+message.nomeTavolo+'_'+message.cameriere+'_cross" onclick="changeStatus(this.className)"/> <img src="/database/spunta-verde.png" style="visibility: hidden " class="'+message.nomeTavolo+'_'+message.cameriere+'_tic" onclick="changeStatus(this.className)"/>';
        persone.style.width="15%";
        persone.innerHTML=message.persone;
        tavolo.style.width="15%";
        tavolo.innerHTML=message.numeroTavolo;
        nome.style.width="15%";
        nome.innerHTML=message.nomeTavolo;
        piatti.style.width="40%";
        var timestamp = new Date().getTime();
        console.log(timestamp);
        piatti.innerHTML = "<table id='piatti_"+timestamp+"'></table>";
        fill(message, timestamp);
    }
}