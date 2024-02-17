
function prova(nome){
    if(document.getElementById("quanta"+nome).style.visibility == "visible"){
        document.getElementById("quanta"+nome).style.visibility = "hidden";
    }else
        document.getElementById("quanta"+nome).style.visibility = "visible";
}
function note(nome){
    if(document.getElementById("note"+nome).style.visibility == "visible"){
        document.getElementById("note"+nome).style.visibility = "hidden";
    }else
        document.getElementById("note"+nome).style.visibility = "visible";
}
function Hide(id){
    if(document.getElementById("tavolo"+id).style.display != "none"){
        document.getElementById("tavolo"+id).style.display = "none";
    }else
        document.getElementById("tavolo"+id).style.display = "block";
}
function HideMenu(){
    if(document.getElementById("menu").style.display != "none"){
        document.getElementById("menu").style.display = "none";
    }else
        document.getElementById("menu").style.display = "block";
}