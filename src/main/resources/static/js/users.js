document.getElementById("searchInput").addEventListener("keyup", function() {
    var input = this.value.toLowerCase();
    var table = document.getElementById("dataTable");
    var rows = table.getElementsByTagName("tr");

    for (var i = 1; i < rows.length; i++) {
        var cells = rows[i].getElementsByTagName("td");
        var match = false;

        for (var j = 0; j < cells.length; j++) {
            var cellText = cells[j].innerText.toLowerCase();

            if (cellText.indexOf(input) > -1) {
                match = true;
                break;
            }
        }

        rows[i].style.display = match ? "" : "none";
    }
});
