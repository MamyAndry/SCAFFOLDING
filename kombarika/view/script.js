$(document).ready(function() {
    // Fetch and display district data
    fetchDistrictData();

    // Add event listener for the "Add District" button
    $('#insert-button').click(function() {
        // Handle inserting logic here
        insertDistrict();
    });
});

function fetchDistrictData() {
    $.ajax({
        url: 'http://localhost:8080/district',
        type: 'GET',
        success: function(data) {
            // Process and display district data
            displayDistrictData(data);
        },
        error: function(error) {
            console.log('Error:', error);
        }
    });
}

function displayDistrictData(data) {
    var tbody = $('#table-body');
    var keys = Object.keys(data[0]);

    for (let i = 0; i < data.length; i++) {
        // Create and append rows to the table
        var tr = $('<tr></tr>');

        for (let j = 0; j < keys.length; j++) {
            // Populate each cell with data
            var temp = data[i][keys[j]];
            if (temp !== null && typeof temp === 'object' && !Array.isArray(temp) && !(temp instanceof Function)) {
                tr.append('<td>' + temp.nomRegion + '</td>');
            } else {
                tr.append('<td>' + temp + '</td>');
            }
        }

        // Append buttons and modals
        appendButtonsAndModals(tr, data[i]);

        tbody.append(tr);
    }
}

function appendButtonsAndModals(tr, rowData) {
    // Append Delete button
    tr.append('<td><button id="delete-' + rowData["id"] + '" class="btn btn-danger">Delete</button></td>');

    // Append Update button and modal
    var updateModal = createUpdateModal(rowData);
    tr.append('<td><button data-toggle="modal" data-target="#updatemodal-' + rowData["id"] + '" id="update-button-' + rowData["id"] + '" class="btn btn-primary">Update</button></td>');
    $('body').append(updateModal);

    // Add event listener for the Delete button
    $('#delete-' + rowData["id"]).click(function() {
        deleteDistrict(rowData);
    });

    // Add event listener for the Update button
    $('#update-button-' + rowData["id"]).click(function() {
        updateDistrict(rowData);
    });
}

function createUpdateModal(rowData) {
    // Generate HTML for the Update modal
    return `
        <div class="modal fade" id="updatemodal-${rowData["id"]}" role="dialog">
            <!-- ... Modal content ... -->
        </div>
    `;
}

function insertDistrict() {
    // Handle inserting logic here
}

function deleteDistrict(rowData) {
    // Handle deleting logic here
}

function updateDistrict(rowData) {
    // Handle updating logic here
}
