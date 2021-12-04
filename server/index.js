var app = require('express')();
var server = require('http').Server(app);
var io = require('socket.io')(server);

server.listen(8080, function(){
    console.log("sisisi");
});

io.on('connection', function(socket){
    console.log("Jugador Conectado!");
    socket.on('disconnect', function(){
        console.log("Jugador Desconectado");
    });
});