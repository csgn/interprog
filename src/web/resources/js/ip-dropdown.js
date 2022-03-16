const toggleDropdown = (dropdown, option) => {
	const ip_dropdown = document.getElementById(`${dropdown}_dropdown`);
	console.log(ip_dropdown)
	switch (option) {
		case 0:
			if (!ip_dropdown.classList.contains("active"))
				ip_dropdown.classList.toggle("active");
			break;
		case 1:
			ip_dropdown.classList.remove("active");
			break;
	}
};

const setOption = (option) => {
	const input = option.parentElement.parentElement.previousElementSibling.firstElementChild;

	if (input.value === "") {
		input.value = option.firstElementChild.textContent;
	}

	option.classList.toggle("active");
};

const filterOptions = (dropdown, input) => {
	let ip_dropdown = document.getElementById(`${dropdown}_dropdown`);
	const options = ip_dropdown.querySelectorAll(".ip-dropdown_option");

	let i = 0;
	for (const option of options) {
		if (
						option.firstElementChild.textContent
						.toLocaleLowerCase()
						.indexOf(input.value.toLowerCase()) !== -1
						) {
			option.style.display = "block";
		} else {
			option.style.display = "none";
			i++;
		}
	}

	const searchError = ip_dropdown.querySelector(".ip-dropdown_search_error");

	if (i === options.length) {
		if (searchError.classList.contains("active"))
			return;
		searchError.classList.toggle("active");
	} else {
		searchError.classList.remove("active");
	}
};

window.onload = () => {
	document.body.onclick = (e) => {
		const dropdownMenus = document.querySelectorAll(".ip-dropdown_menu");
		const target = e.target;
		const targetClassName = target.getAttribute("class");

		switch (targetClassName) {
			case "ip-input":
				const dropdownMenu = target.parentElement.nextElementSibling.firstElementChild;

				dropdownMenus.forEach(e => {
					if (e.classList.contains("active")) {
						if (e !== dropdownMenu) {
							e.classList.remove("active");
						}
					}
				});
				break;
			case "ip-dropdown_menu":
			case "ip-dropdown_option":
			case "ip-dropdown_option active":
				const siblings = Object.entries(e.target.parentElement.children).filter(sibling => {
					!e.target.getAttribute("class").includes(sibling[1].getAttribute("class"));
				});

				if (siblings.length === 0)
					return;
				break;
			default:
				dropdownMenus.forEach(e => {
					e.classList.remove("active");
				});
				break;
		}
	};
};

