$.ajax({
    url: 'http://192.168.1.118:8080/rememberthename/api/customer/',
    async: true,
    success: successCallback,
    error: errorCallback
});

var id = 1;
var idDelete = 1;

function successCallback(response) {

    response.forEach(element => {

        $('#rememberYourNameClients').append('<tr><tr/>');





        $('#rememberYourNameClients tr:last').append('<td>' + element['firstName'] + '</td>')
        $('#rememberYourNameClients tr:last').append('<td>' + element['email'] + '</td>')
        $('#rememberYourNameClients tr:last').append('<td>' + element['phone'] + '</td>')
        $('#rememberYourNameClients tr:last').append('<td>' + element['notes'] + '</td>')



    });

}

function errorCallback(request, response, error) {

    console.log(request);
    successCallback();

}