document.addEventListener("DOMContentLoaded", function() {
    const itemList = document.querySelectorAll(".item-list li");

    itemList.forEach(item => {
        item.addEventListener("click", function() {
            const itemId = this.getAttribute("data-id");
            loadItemDetails(itemId);
        });
    });

    function loadItemDetails(itemId) {
        const rightContent = document.getElementById("right-content");
        // Assuming you have a server endpoint to fetch item details
        const url = `/get-item-details/${itemId}`;

        // Using fetch to make an AJAX request
        fetch(url)
            .then(response => response.json())
            .then(data => {
                // Assuming data contains image URL
                const imageUrl = data.imageUrl;

                // Creating an image element and appending it to the right content
                const imageElement = document.createElement("img");
                imageElement.src = imageUrl;
                rightContent.innerHTML = "";
                rightContent.appendChild(imageElement);
            })
            .catch(error => {
                console.error("Error fetching item details:", error);
            });
    }

});