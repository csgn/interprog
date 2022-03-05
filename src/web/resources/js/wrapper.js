/*
 @author csgn
*/

const navbar = document.querySelector('.navbar');
const wrapper = document.querySelector('.wrapper');

navbar.addEventListener("mouseenter", () => {
	wrapper.classList.toggle("active");
})

navbar.addEventListener("mouseleave", () => {
	wrapper.classList.toggle("active");
})