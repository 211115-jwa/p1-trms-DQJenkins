async function viewRequestsByEmpId() {
    let empId = document.getElementById('userInput').value;
    console.log(empId);
    let response = await fetch('http://localhost:8080/requests/requestor/' + empId);

    if (response.status === 200 || response == '') {
        let requests = await response.json();
        console.log(requests);
        showRequests(requests);
    }
}
function showRequests(requests) {
    let requestsTable = document.getElementById('requests');
    console.log("TEST");
    
    let i = 0;
    for (let req of requests) {
        //Fix for submittedAt formatting
        let submitted = requests[i].submittedAt;
        for (let j = 0; j < 6; j++) {
            if (submitted[j] < 10) {
                submitted[j] = ('0' + submitted[j]);
            }
        }
        sub = (submitted[0] + '/' + submitted[1] + '/' + submitted[2] + ' ' + submitted[3] + ':' + submitted[4] + ':' + submitted[5]);

        //Fix for eventDate formatting
        let eventDate = (requests[i].eventDate);
        for (let j = 0; j < 3; j++) {
            if (eventDate[j] < 10) {
                eventDate[j] = ('0' + eventDate[j]);
            }
        }
        eDate = (eventDate[1] + '/' + eventDate[2] + '/' + eventDate[0]);

        //Fix for eventTime formatting
        let eventTime = (requests[i].eventTime);
        for (let j = 0; j < 2; j++) {
            if (eventTime[j] < 10) {
                eventTime[j] = ('0' + eventTime[j]);
            }
        }
        eTime = (eventTime[0] + ':' + eventTime[1]);

        let status = (requests[i].status);
        stat = (status.name + ':\n' + status.approver);
        let eventType = (requests[i].eventType);
        eType = (eventType.name);
        let gradingFormat = (requests[i].gradingFormat);
        gFormat = (gradingFormat.name);
        let employees = (requests[i].requestor);

        let rowForRequests = document.createElement('tr');

        for (let field in req) {
            if (field == 'requestor') {
                let column = document.createElement('td');
                column.innerText = employees.empId;
                rowForRequests.appendChild(column);
            } else if (field == 'eventDate') {
                let column = document.createElement('td');
                column.innerText = (eDate);
                rowForRequests.appendChild(column);
            } else if (field == 'eventTime') {
                let column = document.createElement('td');
                column.innerText = (eTime);
                rowForRequests.appendChild(column);
            } else if (field == 'gradingFormat') {
                let column = document.createElement('td');
                column.innerText = (gFormat);
                rowForRequests.appendChild(column);
            } else if (field == 'eventType') {
                let column = document.createElement('td');
                column.innerText = (eType);
                rowForRequests.appendChild(column);
            } else if (field == 'status') {
                let column = document.createElement('td');
                column.innerText = (stat);
                rowForRequests.appendChild(column);
            } else if (field == 'submittedAt') {
                let column = document.createElement('td');
                column.innerText = (sub);
                rowForRequests.appendChild(column);
            } else {
                let column = document.createElement('td');
                column.innerText = req[field];
                rowForRequests.appendChild(column);
            }
        }
        i++;
        requestsTable.appendChild(rowForRequests);
    }
}