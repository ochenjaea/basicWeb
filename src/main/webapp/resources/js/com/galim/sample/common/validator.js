var phoneExp = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})?[0-9]{3,4}?[0-9]{4}$/;
var macExp = /^(([0-9a-fA-F]{4}\.){2}[0-9a-fA-F]{4}|([0-9a-fA-F]{2}:){5}[0-9a-fA-F]{2}|([0-9a-fA-F]{12}))$/;

function isPhoneNo(str) {
	return phoneExp.test(str);
}

function isMacNo(str) {
	return macExp.test(str);
}