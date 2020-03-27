var http = require('http');
var fs = require('fs');
var url = require('url');

http.createServer(function (req, res){
    var q = url.parse(req.url, true);
    var filename = "." + q.pathname;
    if(filename=='./'){
        filename = "./MathGame.html";        
    }
    fs.readFile(filename, function(err, data){
        if(err){
            res.writeHead(404, {'Content-Type': 'text/html'});
            return res.end("404 Not Found");
        }
        res.writeHead(200, {'Content-Type': 'text/html'});
        console.log("...Incoming Request: " + req.url);
        res.write(data);
        return res.end();
    })
}).listen(8081);

console.log("Server Running on Port 8081...");