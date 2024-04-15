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

function sendPrivateMessage() {
    var nome = document.getElementById('nome').value;
    var num = document.getElementById('num').value;
    var persone = document.getElementById('persone').value;
    var cameriere = document.getElementById('cameriere').value;
    var pasta = document.getElementById('quantapasta').value;
    document.getElementById('quantapasta').value='';
    var dolce = document.getElementById('quantadolce').value;
    document.getElementById('quantadolce').value='';
    var carne = document.getElementById('quantacarne').value;
    document.getElementById('quantacarne').value='';

    // var elementi = document.querySelectorAll('input[type="number"]')

    privateStompClient.send("/app/private", {},
        JSON.stringify({'nomeTav':nome,'numTav':num, 'persTav':persone,
        'cameriere':cameriere, 'pasta':pasta, 'carne':carne, 'dolce':dolce}));
}
function sendPrivateMessage2() {
    privateStompClient.send("/app/print", {},"stampa");
}

function show(message) {
    var response;
    if(message.nomeTavolo != null) {
        response = document.getElementById('tabella');
        var row = response.insertRow(0);
        let casella=row.insertCell(0);
        let tavolo=row.insertCell(1);
        let nome=row.insertCell(2);
        let cameriere=row.insertCell(3);
        let piatti=row.insertCell(4);
        casella.style.width="15%";
        casella.innerHTML='<img src="/database/inCorso.jpg" class="'+message.nomeTavolo+'_'+message.cameriere+'_cross" onclick="changeStatus(this.className)"/> <img src="/database/spunta-verde.png" style="visibility: hidden " class="'+message.nomeTavolo+'_'+message.cameriere+'_tic" onclick="changeStatus(this.className)"/>';
        row.id= message.nomeTavolo +"--"+message.cameriere;
        tavolo.style.width="20%";
        nome.style.width="20%";
        cameriere.style.width="20%";
        piatti.style.width="40%";
        tavolo.innerHTML=message.numeroTavolo;
        nome.innerHTML=message.nomeTavolo;
        piatti.innerHTML="<table id='piatti'></table>";
        cameriere.innerHTML=message.servitore;
        fill(message);
    }
}