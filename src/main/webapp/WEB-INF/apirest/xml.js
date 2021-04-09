var javabank;

if (window.XMLHttpRequest) {

    javabank = new XMLHttpRequest();
} else if (window.ActiveXObject) {

    javabank = new ActiveXObject('Microsoft.XMLHTTP');
}

javabank.onreadystatechange = function() {

    if (javabank.readyState === 4 && javabank.status === 200) {

        var javabankTable = document.getElementById('javabankTable')

        var objJavabank = JSON.parse(javabank.responseText)

        objJavabank.forEach(element => {

            var row = javabankTable.insertRow(-1);

            Object.keys(element).forEach((property, index) => {

                if (property == "id") {
                    return
                }

                var cell = row.insertCell(index - 1)

                cell.innerHTML = element[property]

            })

            /*           
            
            var row = javabankTable.insertRow(-1)
            var cell = row.insertCell(0);
            var cell1 = row.insertCell(1);
            var cell2 = row.insertCell(2);
            var cell3 = row.insertCell(3);

            cell.innerHTML = element.firstName
            cell1.innerHTML = element.lastName
            cell2.innerHTML = element.email
            cell3.innerHTML = element.phone

            */

        });

    }
};


javabank.open('GET', 'http://localhost:8080/rememberthename/api/customer', true);
javabank.setRequestHeader('Content-type', 'application/json');
javabank.send();