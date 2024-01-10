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
        let persone=row.insertCell(0);
        let tavolo=row.insertCell(1);
        let nome=row.insertCell(2);
        let piatti=row.insertCell(3);
        persone.innerHTML=message.persone;
        tavolo.innerHTML=message.numeroTavolo;
        nome.innerHTML=message.nomeTavolo;
        piatti.innerHTML="<table id='piatti'></table>";
        const temp = document.getElementById("piatti");
        for( let o = 0; o<message.piatti.length; o++){
            row=temp.insertRow(0);
            let nomePiatto=row.insertCell(0);
            let quantità=row.insertCell(1);
            nomePiatto.innerHTML=message.piatti[o].nome;
            quantità.innerHTML=message.piatti[o].quantity;
        }


        // p.innerHTML = "<td id='" + message.nomeTavolo + "--" + message.cameriere + "'> nome:" +
        //     message.nomeTavolo + " numero:" + message.numeroTavolo
        //     + " coperti:" + message.persone + "</td>";
    }else if(message.valore != null){
        response = document.getElementById('messages');
        response.innerHTML = message.valore;
    }else{
        response = document.getElementById('messages');
        response.innerHTML = "<div id= ordiniAttivi>";
        for(let i = 0; i< message.length; i++){
            response.innerHTML += "nome: "+ message[i].nomeTavolo + " numero: " +message[i].numeroTavolo
                + " persone: " + message[i].persone+" cameriere: "+message[i].cameriere +" piatti: <br/>" ;
            for( let o = 0; o<message[i].piatti.length; o++){
                response.innerHTML += message[i].piatti[o].nome + ": "+message[i].piatti[o].quantity+"<br/>";
            }
            response.innerHTML += "<br/><br/>";
        }
        response.innerHTML +="</div>";
    }
}