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
    const bm = document.getElementById('quanta_tris_di_bruschette').value;
    document.getElementById('quanta_tris_di_bruschette').value='';
    const notebm = document.getElementById('note_tris_di_bruschette').value;
    document.getElementById('note_tris_di_bruschette').value='';
    const suppli = document.getElementById('quanta_tris_di_suppli').value;
    document.getElementById('quanta_tris_di_suppli').value='';
    const notesuppli = document.getElementById('note_tris_di_suppli').value;
    document.getElementById('note_tris_di_suppli').value='';
    const pdg = document.getElementById('quanta_primo_del_giorno').value;
    document.getElementById('quanta_primo_del_giorno').value='';
    const notepdg = document.getElementById('note_primo_del_giorno').value;
    document.getElementById('note_primo_del_giorno').value='';
    const primo = document.getElementById('quanta_ciriole_alla_ternana').value;
    document.getElementById('quanta_ciriole_alla_ternana').value='';
    const noteprimo = document.getElementById('note_ciriole_alla_ternana').value;
    document.getElementById('note_ciriole_alla_ternana').value='';
    const gm = document.getElementById('quanta_grigliata_mista').value;
    document.getElementById('quanta_grigliata_mista').value='';
    const notegm = document.getElementById('note_grigliata_mista').value;
    document.getElementById('note_grigliata_mista').value='';
    const bra = document.getElementById('quanta_braciola').value;
    document.getElementById('quanta_braciola').value='';
    const notebraciola = document.getElementById('note_braciola').value;
    document.getElementById('note_braciola').value='';
    const agnello = document.getElementById('quanta_agnello').value;
    document.getElementById('quanta_agnello').value='';
    const noteagnello = document.getElementById('note_agnello').value;
    document.getElementById('note_agnello').value='';
    const vitella = document.getElementById('quanta_vitella').value;
    document.getElementById('quanta_vitella').value='';
    const notevitella = document.getElementById('note_vitella').value;
    document.getElementById('note_vitella').value='';
    const sals = document.getElementById('quanta_tris_di_salsicce').value;
    document.getElementById('quanta_tris_di_salsicce').value='';
    const notesals = document.getElementById('note_tris_di_salsicce').value;
    document.getElementById('note_tris_di_salsicce').value='';
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
    const acqua1 = document.getElementById('quanta_acqua_1Lt').value;
    document.getElementById('quanta_acqua_1Lt').value='';
    const noteAcqua1 = document.getElementById('note_acqua_1Lt').value;
    document.getElementById('note_acqua_1Lt').value='';
    const acqua2 = document.getElementById('quanta_acqua_0.5Lt').value;
    document.getElementById('quanta_acqua_0.5Lt').value='';
    const noteAcqua2 = document.getElementById('note_acqua_0.5Lt').value;
    document.getElementById('note_acqua_0.5Lt').value='';
    const vino1 = document.getElementById('quanta_vino_1Lt').value;
    document.getElementById('quanta_vino_1Lt').value='';
    const noteVino1 = document.getElementById('note_vino_1Lt').value;
    document.getElementById('note_vino_1Lt').value='';
    const vino2 = document.getElementById('quanta_vino_0.5Lt').value;
    document.getElementById('quanta_vino_0.5Lt').value='';
    const noteVino2 = document.getElementById('note_vino_0.5Lt').value;
    document.getElementById('note_vino_0.5Lt').value='';
    const vino3 = document.getElementById('quanta_vino_in_bottiglia').value;
    document.getElementById('quanta_vino_in_bottiglia').value='';
    const noteVino3 = document.getElementById('note_vino_in_bottiglia').value;
    document.getElementById('note_vino_in_bottiglia').value='';
    const celiaco = document.getElementById('quanta_piatti_senza_glutine').value;
    document.getElementById('quanta_piatti_senza_glutine').value='';
    const noteceliaco = document.getElementById('note_piatti_senza_glutine').value;
    document.getElementById('note_piatti_senza_glutine').value='';
    const bibita = document.getElementById('quanta_bibita').value;
    document.getElementById('quanta_bibita').value='';
    const noteBibita = document.getElementById('note_bibita').value;
    document.getElementById('note_bibita').value='';
    const birra1 = document.getElementById('quanta_birra_piccola').value;
    document.getElementById('quanta_birra_piccola').value='';
    const noteBirra1 = document.getElementById('note_birra_piccola').value;
    document.getElementById('note_birra_piccola').value='';
    const birra2 = document.getElementById('quanta_birra_grande').value;
    document.getElementById('quanta_birra_grande').value='';
    const noteBirra2 = document.getElementById('note_birra_grande').value;
    document.getElementById('note_birra_grande').value='';
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
        JSON.stringify({'nomeTav':nome,'numTav':num, 'persTav':persone, 'cameriere':cameriere,
            'antipasto di campomaggiore':adc,'note antipasto di campomaggiore':noteadc,
            'tris di bruschette':bm, 'note tris di bruschette':notebm,
            'tris di suppli':suppli, 'note tris di suppli':notesuppli,
            'primo del giorno':pdg,'note primo del giorno':notepdg,
            'ciriole alla ternana':primo, 'note ciriole alla ternana':noteprimo,
            'grigliata mista':gm, 'note grigliata mista':notegm,
            'braciola':bra, 'note braciola':notebraciola,
            'agnello':agnello, 'note agnello':noteagnello,
            'vitella':vitella, 'note vitella':notevitella,
            'tris di salsicce':sals, 'note tris di salsicce':notesals,
            'verdura cotta':verd, 'note verdura cotta':noteverd, 
            'patate fritte':pat, 'note patate fritte':notepat,
            'pizza 1 ingrediente':pizza1, 'note pizza 1 ingrediente':notepizza1,
            'pizza 2 ingredienti':pizza2, 'note pizza 2 ingredienti':notepizza2, 
            'pizzola con nutella':piznut, 'note pizzola con nutella':notepiznut,
            'pizzola dolce/salata':pizzola, 'note pizzola dolce/salata':notepizzola,
            'acqua 1Lt':acqua1, 'note acqua 1Lt':noteAcqua1,
            'acqua 0.5Lt':acqua2, 'note acqua 0.5Lt':noteAcqua2,
            'vino 1Lt':vino1, 'note vino 1Lt':noteVino1,
            'vino 0.5Lt':vino2, 'note vino 0.5Lt':noteVino2,
            'vino in bottiglia':vino3, 'note vino in bottiglia':noteVino3,
            'piatti senza glutine':celiaco, 'note piatti senza glutine':noteceliaco,
            'bibita':bibita, 'note bibita':noteBibita,
            'birra piccola':birra1, 'note birra piccola':noteBirra1,
            'birra grande':birra2, 'note birra grande':noteBirra2,
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