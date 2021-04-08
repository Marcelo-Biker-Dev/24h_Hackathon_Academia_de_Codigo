window.onload = function() {

    var customerData = [{
        "id": 1,
        "firstName": "Rui",
        "lastName": "Ferrão",
        "email": "rui@gmail.com",
        "phone": "777888"
    }, {
        "id": 2,
        "firstName": "Sergio",
        "lastName": "Gouveia",
        "email": "sergio@gmail.com",
        "phone": "777999"
    }, {
        "id": 3,
        "firstName": "Bruno",
        "lastName": "Ferreira",
        "email": "bruno@gmail.com",
        "phone": "777666"
    }, {
        "id": 4,
        "firstName": "Rodolfo",
        "lastName": "Matos",
        "email": "rodolfo@gmail.com",
        "phone": "777333"
    }];

    var javabankTable = document.getElementById('javabankTable')

    customerData.forEach(element => {

        var row = javabankTable.insertRow(-1)

        var cell = row.insertCell(0);
        var cell1 = row.insertCell(1);
        var cell2 = row.insertCell(2);
        var cell3 = row.insertCell(3);

        cell.innerHTML = element.firstName
        cell1.innerHTML = element.lastName
        cell2.innerHTML = element.email
        cell3.innerHTML = element.phone

    });

}