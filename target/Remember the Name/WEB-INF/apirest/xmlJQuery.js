$.ajax({
    url: 'http://localhost:8080/rememberthename/api/customer',
    async: true,
    success: successCallback,
    error: errorCallback
});

var id = 1;
var idDelete = 1;

function successCallback(response) {

    response.forEach(element => {

        $('#javabankTable').append('<tr><tr/>');

        Object.keys(element).forEach(property => {

            if (property == "id") {
                return
            }

            $('#javabankTable tr:last').append('<td>' + element[property] + '</td>')

        })
        $('#javabankTable tr:last').append('<td><a href=' + /*element['id']*/"/view.html>View</a></td>")
        $('#javabankTable tr:last').append('<td><a href=' + element['id'] + "/edit.html>Edit</a></td>")
        $('#javabankTable tr:last').append('<td><a href=' + element['id'] + "/delete.html> Delete</a></td>")

    });

}

/* DELETE */


$(".delete").click(function(e) {
    console.log("hiiiiiiiiiiiiiii")
    e.preventDefault();
});


$(".delete").click(function(event) {

    $.ajax({
        url: 'http://localhost:8080/rememberthename/api/customer/1',
        type: 'DELETE',
        async: true,
        contentType: 'application/json;charset=UTF-8',
        success: successCallbackTwo,
        error: errorCallback

    });
});


function errorCallback(request, status, error) {
    console.log("error")
}

function redirect() {
    window.location = "formADD.html"

}

function errorCallbackTwo(request, status, error) {
    console.log("error")
}