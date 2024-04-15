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
    var notepasta = document.getElementById('notepasta').value;
    document.getElementById('notepasta').value='';
    var dolce = document.getElementById('quantadolce').value;
    document.getElementById('quantadolce').value='';
    var notedolce = document.getElementById('notedolce').value;
    document.getElementById('notedolce').value='';
    var carne = document.getElementById('quantacarne').value;
    document.getElementById('quantacarne').value='';
    var notecarne = document.getElementById('notecarne').value;
    document.getElementById('notecarne').value='';

    var selectElement = document.getElementById('cameriere');
    for (var i = 0; i < selectElement.options.length; i++) {
        // Get the current option
        var option = selectElement.options[i];
        // Change the text and value of the option
        if(option.text.split("-")[0] === cameriere) {
            var number = parseInt(option.text.split("-")[1],10);
            number++;
            option.text = cameriere + '-' + number;
        }
    }

    // var elementi = document.querySelectorAll('input[type="number"]')

    privateStompClient.send("/app/private", {},
        JSON.stringify({'nomeTav':nome,'numTav':num, 'persTav':persone,
            'cameriere':cameriere, 'pasta':pasta, 'carne':carne, 'dolce':dolce,
            'notepasta':notepasta, 'notedolce':notedolce, 'notecarne':notecarne}));
}
function sendPrivateMessage2() {
    privateStompClient.send("/app/print", {},"stampa");
}

function show(message) {
    var response;
    if(message.valore != null){
        if(message.valore.includes("totale:")) {
            response = document.getElementById('messages');
            response.innerHTML = message.valore;
        }else{
            // Get the select element
            var selectElement = document.getElementById('cameriere');

            // Loop over the options in the select element
            for (var i = 0; i < selectElement.options.length; i++) {
                // Get the current option
                var option = selectElement.options[i];

                // Change the text and value of the option
                if(option.text.split("-")[0] === message.valore.split("-")[0]) {
                    option.text = message.valore;  // Replace with the new text you want
                }
            }
        }
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