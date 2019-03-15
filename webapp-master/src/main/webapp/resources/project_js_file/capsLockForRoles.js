 $(function () {
        	 $("#error").hide();
        	 $("#error1").hide();
            var isShiftPressed = false;
            var isCapsOn = null;
            $("#c_password").bind("keydown", function (e) {
                var keyCode = e.keyCode ? e.keyCode : e.which;
                if (keyCode == 16) {
                    isShiftPressed = true;
                }
            });
            $("#c_password").bind("keyup", function (e) {
                var keyCode = e.keyCode ? e.keyCode : e.which;
                if (keyCode == 16) {
                    isShiftPressed = false;
                }
                if (keyCode == 20) {
                    if (isCapsOn == true) {
                        isCapsOn = false;
                        $("#error").hide();
                    } else if (isCapsOn == false) {
                        isCapsOn = true;
                        $("#error").show();
                    }
                }
            });
            $("#c_password").bind("keypress", function (e) {
                var keyCode = e.keyCode ? e.keyCode : e.which;
                if (keyCode >= 65 && keyCode <= 90 && !isShiftPressed) {
                    isCapsOn = true;
                    $("#error").show();
                } else {
                    $("#error").hide();
                }
            });
            
            $("#c_password_new").bind("keydown", function (e) {
                var keyCode = e.keyCode ? e.keyCode : e.which;
                if (keyCode == 16) {
                    isShiftPressed = true;
                }
            });
            $("#c_password_new").bind("keyup", function (e) {
                var keyCode = e.keyCode ? e.keyCode : e.which;
                if (keyCode == 16) {
                    isShiftPressed = false;
                }
                if (keyCode == 20) {
                    if (isCapsOn == true) {
                        isCapsOn = false;
                        $("#error1").hide();
                    } else if (isCapsOn == false) {
                        isCapsOn = true;
                        $("#error1").show();
                    }
                }
            });
            $("#c_password_new").bind("keypress", function (e) {
                var keyCode = e.keyCode ? e.keyCode : e.which;
                if (keyCode >= 65 && keyCode <= 90 && !isShiftPressed) {
                    isCapsOn = true;
                    $("#error1").show();
                } else {
                    $("#error1").hide();
                }
            });
            
            
            $("#c_password_new_repeat").bind("keydown", function (e) {
                var keyCode = e.keyCode ? e.keyCode : e.which;
                if (keyCode == 16) {
                    isShiftPressed = true;
                }
            });
            $("#c_password_new_repeat").bind("keyup", function (e) {
                var keyCode = e.keyCode ? e.keyCode : e.which;
                if (keyCode == 16) {
                    isShiftPressed = false;
                }
                if (keyCode == 20) {
                    if (isCapsOn == true) {
                        isCapsOn = false;
                        $("#error1").hide();
                    } else if (isCapsOn == false) {
                        isCapsOn = true;
                        $("#error1").show();
                    }
                }
            });
            $("#c_password_new_repeat").bind("keypress", function (e) {
                var keyCode = e.keyCode ? e.keyCode : e.which;
                if (keyCode >= 65 && keyCode <= 90 && !isShiftPressed) {
                    isCapsOn = true;
                    $("#error1").show();
                } else {
                    $("#error1").hide();
                }
            });            
            
        });