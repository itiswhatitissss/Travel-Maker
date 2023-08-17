const images = [
    "/background.jpg",
    "/스크립트.jpg",
    // Add more image paths here
];

const imageElement = document.getElementById("image");
const prevBtn = document.getElementById("prevBtn");
const nextBtn = document.getElementById("nextBtn");

let currentIndex = 0;

function updateImage() {
    imageElement.src = images[currentIndex];
}

prevBtn.addEventListener("click", () => {
    if (currentIndex > 0) {
        currentIndex--;
        updateImage();
    }
});

nextBtn.addEventListener("click", () => {
    if (currentIndex < images.length - 1) {
        currentIndex++;
        updateImage();
    }
});

updateImage();
