const toggleDropdown = (option) => {
	let ip_dropdown = document.querySelector("#ip-dropdown");
	
	switch (option) {
		case 0:
			ip_dropdown.classList.toggle("active");
			break;
		case 1:
		ip_dropdown.classList.remove("active");
	}

	if (ip_dropdown.classList.contains("active"))
		return;
}
