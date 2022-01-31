/*
 * ATTENTION: The "eval" devtool has been used (maybe by default in mode: "development").
 * This devtool is neither made for production nor for readable output files.
 * It uses "eval()" calls to create a separate source file in the browser devtools.
 * If you are trying to read the output file, select a different devtool (https://webpack.js.org/configuration/devtool/)
 * or disable the default devtool with "devtool: false".
 * If you are looking for production-ready output files, see mode: "production" (https://webpack.js.org/configuration/mode/).
 */
/******/ (function() { // webpackBootstrap
/******/ 	var __webpack_modules__ = ({

/***/ "./src/assets/js/app.js":
/*!******************************!*\
  !*** ./src/assets/js/app.js ***!
  \******************************/
/***/ (function() {

eval("$(function () {\r\n    $(\"#add-car\").submit(function (event) {\r\n        event.preventDefault();\r\n\r\n        let car = {\r\n            number: $(\"#car-number\").val(),\r\n            model: $(\"#car-model\").val(),\r\n            year: $(\"#car-year\").val(),\r\n            color: $(\"#car-color\").val(),\r\n            body: $(\"input[name=body]:checked\").val()\r\n        };\r\n\r\n        $.ajax({\r\n            url: \"/cars\",\r\n            data: JSON.stringify(car),\r\n            type: \"POST\",\r\n            dataType: \"json\",\r\n            contentType: \"application/json\",\r\n\r\n            beforeSend: function (xhr) {\r\n                xhr.setRequestHeader(\"Accept\", \"application/json\");\r\n                xhr.setRequestHeader(\"Content-Type\", \"application/json\");\r\n            },\r\n\r\n            success: function (data) {\r\n                let messages = [\"\", \"\", \"\"];\r\n\r\n                if (!data.isNumberValid) {\r\n                    messages[0] = \"Invalid car number\";\r\n                }\r\n\r\n                if (!data.isYearValid) {\r\n                    messages[1] = \"Year must be between 1950 and now\";\r\n                }\r\n\r\n                if (!data.isUnique){\r\n                    messages[2] = \"Car is already exist!\"\r\n                }\r\n                console.log(messages);\r\n\r\n                $.each(messages, function (index, value) {\r\n                    if (value !== \"\")\r\n                        new bs5.Toast({\r\n                            body: value,\r\n                            className: \"border-0 bg-danger text-white\",\r\n                            btnCloseWhite: true,\r\n                            placement: \"bottom-right\",\r\n                            delay: 3000,\r\n                        }).show();\r\n                });\r\n\r\n                let isAllFieldsValid = true;\r\n                $.each(messages, function (index, value) {\r\n                    if (value !== \"\") isAllFieldsValid = false;\r\n                });\r\n\r\n                if (isAllFieldsValid) {\r\n                    new bs5.Toast({\r\n                        body: \"Succesfully added\",\r\n                        className: \"border-0 bg-success text-white\",\r\n                        btnCloseWhite: true,\r\n                        placement: \"bottom-right\",\r\n                        delay: 3000,\r\n                    }).show();\r\n                }\r\n            },\r\n        });\r\n    });\r\n});\r\n\n\n//# sourceURL=webpack://tingsha/./src/assets/js/app.js?");

/***/ }),

/***/ "./src/assets/js/bs5-toast.js":
/*!************************************!*\
  !*** ./src/assets/js/bs5-toast.js ***!
  \************************************/
/***/ (function(module) {

eval("(function (global, factory) {\n   true ? module.exports = factory() :\n  0;\n})(this, (function () { 'use strict';\n\n  const bs5 = {\n    Toast: class {\n      #body;\n      #animation;\n      #autohide;\n      #btnClose;\n      #btnCloseWhite;\n      #className;\n      #delay;\n      #gap;\n      #header;\n      #margin;\n      #placement;\n      #pos;\n      constructor(param) {\n        this.#body = this.#setOption(param.body, \"\");\n        this.#animation = this.#setOption(param.animation, true);\n        this.#autohide = this.#setOption(param.autohide, true);\n        this.#btnClose = this.#setOption(param.btnClose, true);\n        this.#btnCloseWhite = this.#setOption(param.btnCloseWhite, false);\n        this.#className = this.#setOption(param.className, \"\");\n        this.#delay = this.#setOption(param.delay, 5e3);\n        this.#gap = this.#setOption(param.gap, 16);\n        this.#header = this.#setOption(param.header, \"\");\n        this.#margin = this.#setOption(param.margin, \"1rem\");\n        this.#placement = this.#setOption(param.placement, \"top-right\");\n        this.#pos = this.#placement.split(\"-\");\n        const closeBtn = `<button type=\"button\" hidden class=\"btn-close flex-shrink-0\" data-bs-dismiss=\"toast\" aria-label=\"Close\"></button>`;\n        let style = `style=\"margin:${this.#margin};${this.#pos[0]}:0;${this.#pos[1]}:${this.#animation ? \"-50%\" : 0};z-index:1081\"`;\n        let template = document.createElement(\"template\");\n        template.innerHTML = `<div class=\"toast position-fixed toast-${this.#placement} ${this.#className}\" ${style} role=\"alert\" aria-live=\"assertive\" aria-atomic=\"true\">\n\t\t\t\t<div class=\"toast-header\" hidden><div class=\"d-flex align-items-center flex-grow-1\">${this.#header}</div>${closeBtn}</div>\n\t\t\t\t<div class=\"toast-body\"><div class=\"d-flex w-100\"><div class=\"flex-grow-1\">${this.#body}</div>${closeBtn}</div></div>\n\t\t\t</div>`;\n        let el = template.content.firstChild;\n        if (el instanceof HTMLDivElement) {\n          const btns = el.querySelectorAll(\".btn-close\");\n          btns.forEach((btn) => {\n            this.#btnClose && btn.removeAttribute(\"hidden\");\n            this.#btnCloseWhite && btn.classList.add(\"btn-close-white\");\n          });\n          if (this.#header !== \"\") {\n            el.querySelector(\".toast-header\").removeAttribute(\"hidden\");\n            btns[1].remove();\n          }\n          this.element = el;\n        }\n        document.body.insertAdjacentElement(\"afterbegin\", this.element);\n        this.bootstrapToast = new bootstrap.Toast(this.element, {\n          animation: this.#animation,\n          autohide: this.#autohide,\n          delay: this.#delay\n        });\n        this.element.addEventListener(\"hidden.bs.toast\", () => {\n          this.element.remove();\n          this.#stack();\n        });\n        this.element.addEventListener(\"show.bs.toast\", () => {\n          let that = this;\n          let timer = setInterval(myFunction, 0);\n          function myFunction() {\n            if (that.element.offsetHeight > 0) {\n              clearInterval(timer);\n              if (that.#animation) {\n                const transition = parseFloat(getComputedStyle(that.element).transitionDuration) * 1e3;\n                that.element.style.transition = `all ${transition * 4}ms cubic-bezier(0.16, 1, 0.3, 1), opacity ${transition}ms linear`;\n                that.element.style[that.#pos[1]] = 0;\n              }\n              that.#stack();\n            }\n          }\n        });\n      }\n      #setOption(param, defaultValue) {\n        return param !== void 0 ? param : defaultValue;\n      }\n      show() {\n        this.bootstrapToast.show();\n      }\n      hide() {\n        this.bootstrapToast.hide();\n      }\n      #stack() {\n        const toasts = document.body.querySelectorAll(`.toast-${this.#placement}`);\n        let yAxis = [];\n        toasts.forEach((el, index) => {\n          if (el instanceof HTMLElement) {\n            index === 0 && yAxis.push(0);\n            if (toasts[index + 1] instanceof HTMLElement) {\n              yAxis.push(yAxis[index] + el.offsetHeight);\n            }\n            el.style[this.#pos[0]] = yAxis[index] + this.#gap * index + \"px\";\n          }\n        });\n      }\n    }\n  };\n\n  return bs5;\n\n}));\n\n\n//# sourceURL=webpack://tingsha/./src/assets/js/bs5-toast.js?");

/***/ })

/******/ 	});
/************************************************************************/
/******/ 	// The module cache
/******/ 	var __webpack_module_cache__ = {};
/******/ 	
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/ 		// Check if module is in cache
/******/ 		var cachedModule = __webpack_module_cache__[moduleId];
/******/ 		if (cachedModule !== undefined) {
/******/ 			return cachedModule.exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = __webpack_module_cache__[moduleId] = {
/******/ 			// no module.id needed
/******/ 			// no module.loaded needed
/******/ 			exports: {}
/******/ 		};
/******/ 	
/******/ 		// Execute the module function
/******/ 		__webpack_modules__[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/ 	
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/ 	
/************************************************************************/
/******/ 	
/******/ 	// startup
/******/ 	// Load entry module and return exports
/******/ 	// This entry module can't be inlined because the eval devtool is used.
/******/ 	__webpack_require__("./src/assets/js/app.js");
/******/ 	var __webpack_exports__ = __webpack_require__("./src/assets/js/bs5-toast.js");
/******/ 	
/******/ })()
;