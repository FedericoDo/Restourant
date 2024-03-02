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
        row.id= message.nomeTavolo +"--"+message.cameriere;
        let tavolo=row.insertCell(0);
        tavolo.style.width="20%";
        let nome=row.insertCell(1);
        nome.style.width="20%";
        let cameriere=row.insertCell(2);
        cameriere.style.width="20%";
        let piatti=row.insertCell(3);
        piatti.style.width="40%";
        tavolo.innerHTML=message.numeroTavolo;
        nome.innerHTML=message.nomeTavolo;
        piatti.innerHTML="<table id='piatti'></table>";
        cameriere.innerHTML=message.servitore;
        const temp = document.getElementById("piatti");
        for( let o = 0; o<message.piatti.length; o++){
            row=temp.insertRow(0);
            let nomePiatto=row.insertCell(0);
            let quantità=row.insertCell(1);
            let note=row.insertCell(2);
            nomePiatto.innerHTML=message.piatti[o].nome;
            quantità.innerHTML=message.piatti[o].quantity;
            note.innerHTML=message.piatti[o].note;
        }
    }
}