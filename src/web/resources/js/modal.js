/* 
 * @author csgn
 */
const toggle = (id, action) => {
	const modal = document.getElementById(`modal-${id}`)

	switch (action) {
		case 0:
			modal.classList.toggle("active")
			break;
		case 1:
			modal.classList.remove("active")
			break;
	}
}