const prevButton = document.querySelector("#prev");
const nextButton = document.querySelector("#next");
const carousel = document.querySelector("#carousel");
const images = document.querySelectorAll("#carousel-item");

let currentIndex = 0;

prevButton.addEventListener("click", () => {
    currentIndex = Math.max(currentIndex - 1, 0);
    updateCarousel();
});

nextButton.addEventListener("click", () => {
    currentIndex = Math.min(currentIndex + 1, images.length - 1);
    updateCarousel();
});

function updateCarousel() {
    const slideAmount = -currentIndex * 500;
    carousel.style.transform = `translateX(${slideAmount}px)`;
}