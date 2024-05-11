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
    const nome = document.getElementById('nome').value;
    const num = document.getElementById('num').value;
    const persone = document.getElementById('persone').value;
    const cameriere = document.getElementById('cameriere').value;
    const adc = document.getElementById('quanta_antipasto_di_campomaggiore').value;
    document.getElementById('quanta_antipasto_di_campomaggiore').value='';
    const noteadc = document.getElementById('note_antipasto_di_campomaggiore').value;
    document.getElementById('note_antipasto_di_campomaggiore').value='';
    const bm = document.getElementById('quanta_bruschette_miste').value;
    document.getElementById('quanta_bruschette_miste').value='';
    const notebm = document.getElementById('note_bruschette_miste').value;
    document.getElementById('note_bruschette_miste').value='';
    const suppli = document.getElementById('quanta_tris_di_suppli').value;
    document.getElementById('quanta_tris_di_suppli').value='';
    const notesuppli = document.getElementById('note_tris_di_suppli').value;
    document.getElementById('note_tris_di_suppli').value='';
    const pdg = document.getElementById('quanta_primo_del_giorno').value;
    document.getElementById('quanta_primo_del_giorno').value='';
    const notepdg = document.getElementById('note_primo_del_giorno').value;
    document.getElementById('note_primo_del_giorno').value='';
    const primo = document.getElementById('quanta_pasta_al_pomodoro').value;
    document.getElementById('quanta_pasta_al_pomodoro').value='';
    const noteprimo = document.getElementById('note_pasta_al_pomodoro').value;
    document.getElementById('note_pasta_al_pomodoro').value='';
    const gm = document.getElementById('quanta_grigliata_mista').value;
    document.getElementById('quanta_grigliata_mista').value='';
    const notegm = document.getElementById('note_grigliata_mista').value;
    document.getElementById('note_grigliata_mista').value='';
    const bra = document.getElementById('quanta_braciola').value;
    document.getElementById('quanta_braciola').value='';
    const notebraciola = document.getElementById('note_braciola').value;
    document.getElementById('note_braciola').value='';
    const sals = document.getElementById('quanta_porzione_di_salsicce').value;
    document.getElementById('quanta_porzione_di_salsicce').value='';
    const notesals = document.getElementById('note_porzione_di_salsicce').value;
    document.getElementById('note_porzione_di_salsicce').value='';
    const verd = document.getElementById('quanta_verdura_cotta').value;
    document.getElementById('quanta_verdura_cotta').value='';
    const noteverd = document.getElementById('note_verdura_cotta').value;
    document.getElementById('note_verdura_cotta').value='';
    const pat = document.getElementById('quanta_patate_fritte').value;
    document.getElementById('quanta_patate_fritte').value='';
    const notepat = document.getElementById('note_patate_fritte').value;
    document.getElementById('note_patate_fritte').value='';
    const pizza1 = document.getElementById('quanta_pizza_1_ingrediente').value;
    document.getElementById('quanta_pizza_1_ingrediente').value='';
    const notepizza1 = document.getElementById('note_pizza_1_ingrediente').value;
    document.getElementById('note_pizza_1_ingrediente').value='';
    const pizza2 = document.getElementById('quanta_pizza_2_ingredienti').value;
    document.getElementById('quanta_pizza_2_ingredienti').value='';
    const notepizza2 = document.getElementById('note_pizza_2_ingredienti').value;
    document.getElementById('note_pizza_2_ingredienti').value='';
    const piznut = document.getElementById('quanta_pizzola_con_nutella').value;
    document.getElementById('quanta_pizzola_con_nutella').value='';
    const notepiznut = document.getElementById('note_pizzola_con_nutella').value;
    document.getElementById('note_pizzola_con_nutella').value='';
    const pizzola = document.getElementById('quanta_pizzola_dolce/salata').value;
    document.getElementById('quanta_pizzola_dolce/salata').value='';
    const notepizzola = document.getElementById('note_pizzola_dolce/salata').value;
    document.getElementById('note_pizzola_dolce/salata').value='';
    const sconto_perc = document.getElementById('sconto_perc').value;
    document.getElementById('sconto_perc').value='';
    const sconto_netto = document.getElementById('sconto_netto').value;
    document.getElementById('sconto_netto').value='';

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
            'cameriere':cameriere,'antipasto di campomaggiore':adc,'note antipasto di campomaggiore':noteadc,'tris di bruschette':bm,
            'note tris di bruschette':notebm,'tris di suppli':suppli,'note tris di suppli':notesuppli, 'primo del giorno':pdg,
            'note primo del giorno':notepdg,'pasta al pomodoro':primo, 'note pasta al pomodoro':noteprimo, 'grigliata mista':gm,
            'note grigliata mista':notegm, 'braciola':bra, 'note braciola':notebraciola, 'tris di salsicce':sals,
            'note tris di salsicce':notesals, 'verdura cotta':verd, 'note verdura cotta':noteverd, 'patate fritte':pat,
            'note patate fritte':notepat, 'pizza 1 ingrediente':pizza1, 'note pizza 1 ingrediente':notepizza1,
            'pizza 2 ingredienti':pizza2, 'note pizza 2 ingredienti':notepizza2, 'pizzola con nutella':piznut,
            'note pizzola con nutella':notepiznut, 'pizzola dolce/salata':pizzola, 'note pizzola dolce/salata':notepizzola,
            'sconto_perc':sconto_perc, 'sconto_netto':sconto_netto}));
}
function sendPrivateMessage2() {
    privateStompClient.send("/app/print", {},"stampa");
}
function sendPrivateMessage3() {
    privateStompClient.send("/app/total", {},"stampa");
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