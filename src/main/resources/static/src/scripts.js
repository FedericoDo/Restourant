var stompClient = null;

$(document).ready(function() {
    console.log("Index page is ready");
    connect();
});

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/user/topic/private-messages', function (message) {
           showMessage(message.body);
        });
    });
}

function showMessage(message) {
    console.log("arrivato");
    $("#messages").innerHTML("<tr><td>" + message + "</td></tr>");
}
function hide(className) {
    const elements = document.getElementsByClassName(className);
    for(let i = 0; i < elements.length; i++) {
        if(elements[i].style.textDecoration=="line-through")
            elements[i].style.textDecoration="none";
        else
            elements[i].style.textDecoration="line-through";
    }
    /*const allElements = document.getElementsByClassName(className[1]);
    let bool=true;
    for(let i = 0; i < allElements.length; i++) {
        if(allElements[i].style.textDecoration=="none")
            bool=false;
    }
    if(bool){
        const cameriere=document.getElementById("hidden").value;
        cambiaImmagine(cameriere);
    }*/

}
function fill(message){
    const temp = document.getElementById("piatti");
    for( let o = 0; o<message.piatti.length; o++){
        row=temp.insertRow(0);
        let nomePiatto=row.insertCell(0);
        let quantità=row.insertCell(1);
        let note=row.insertCell(2);
        nomePiatto.innerHTML=message.piatti[o].nome;
        nomePiatto.className=message.cameriere+message.nomeTavolo+message.piatti[o].nome;
        nomePiatto.onclick=function(){hide(this.className);};
        quantità.innerHTML=message.piatti[o].quantity;
        quantità.className=message.cameriere+message.nomeTavolo+message.piatti[o].nome;
        quantità.onclick=function(){hide(this.className);};
        note.innerHTML=message.piatti[o].note;
        note.className=message.cameriere+message.nomeTavolo+message.piatti[o].nome;
        note.onclick=function(){hide(this.className);};
/*
        document.getElementById('hidden').value=message.cameriere;
*/
    }
}