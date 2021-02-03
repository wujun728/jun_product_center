'use strict';

var _createClass = function () {
    function defineProperties(target, props) {
        for (var i = 0; i < props.length; i++) {
            var descriptor = props[i];
            descriptor.enumerable = descriptor.enumerable || false;
            descriptor.configurable = true;
            if ("value" in descriptor) descriptor.writable = true;
            Object.defineProperty(target, descriptor.key, descriptor);
        }
    }

    return function (Constructor, protoProps, staticProps) {
        if (protoProps) defineProperties(Constructor.prototype, protoProps);
        if (staticProps) defineProperties(Constructor, staticProps);
        return Constructor;
    };
}();

function _classCallCheck(instance, Constructor) {
    if (!(instance instanceof Constructor)) {
        throw new TypeError("Cannot call a class as a function");
    }
}

var Pushbar = function () {
    function Pushbar() {
        var config = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {overlay: true, blur: false};

        _classCallCheck(this, Pushbar);

        this.activeBar = null;
        this.overlay = false;

        if (config.overlay) {
            this.overlay = document.createElement('div');
            this.overlay.classList.add('pushbar_overlay');
            document.querySelector('body').appendChild(this.overlay);
        }

        if (config.blur) {
            var mainContent = document.querySelector('.pushbar_main_content');
            if (mainContent) {
                mainContent.classList.add('pushbar_blur');
            }
        }

        this.bindEvents();
    }

    _createClass(Pushbar, [{
        key: 'handleOpenEvent',
        value: function handleOpenEvent(e) {
            e.preventDefault();
            var pushbarId = e.currentTarget.getAttribute('data-pushbar-target');
            if (pushbarId) {
                this.open(pushbarId);
            }
        }
    }, {
        key: 'handleCloseEvent',
        value: function handleCloseEvent(e) {
            e.preventDefault();
            this.close();
        }
    }, {
        key: 'handleKeyEvent',
        value: function handleKeyEvent(e) {
            if (this.opened && e.keyCode === 27) {
                this.close();
            }
        }
    }, {
        key: 'bindEvents',
        value: function bindEvents() {
            var _this = this;

            var triggers = document.querySelectorAll('[data-pushbar-target]');
            var closers = document.querySelectorAll('[data-pushbar-close]');

            triggers.forEach(function (trigger) {
                return trigger.addEventListener('click', function (e) {
                    return _this.handleOpenEvent(e);
                }, false);
            });
            closers.forEach(function (closer) {
                return closer.addEventListener('click', function (e) {
                    return _this.handleCloseEvent(e);
                }, false);
            });

            if (this.overlay) {
                this.overlay.addEventListener('click', function (e) {
                    return _this.handleCloseEvent(e);
                }, false);
            }
            document.addEventListener('keyup', function (e) {
                return _this.handleKeyEvent(e);
            });
        }
    }, {
        key: 'open',
        value: function open(pushbarId) {
            // Current bar is already opened
            if (String(pushbarId) === this.activeBarId && this.opened) {
                return;
            }

            // Get new pushbar target
            var pushbar = Pushbar.findElementById(pushbarId);

            if (!pushbar) return;

            // Close active bar (if exists)
            if (this.opened) {
                this.close();
            }

            Pushbar.dispatchOpen(pushbar);
            pushbar.classList.add('opened');

            var Root = document.querySelector('html');
            Root.classList.add('pushbar_locked');
            Root.setAttribute('pushbar', pushbarId);
            this.activeBar = pushbar;

            document.getElementById("side-nav-close").style.display = "inline-block";
            document.getElementById("side-nav-open").style.display = "none";
        }
    }, {
        key: 'close',
        value: function close() {
            var activeBar = this.activeBar;

            if (!activeBar) return;

            Pushbar.dispatchClose(activeBar);
            activeBar.classList.remove('opened');

            var Root = document.querySelector('html');
            Root.classList.remove('pushbar_locked');
            Root.removeAttribute('pushbar');

            this.activeBar = null;
            document.getElementById("side-nav-open").style.display = "inline-block";
            document.getElementById("side-nav-close").style.display = "none";
        }
    }, {
        key: 'opened',
        get: function get() {
            var activeBar = this.activeBar;

            return Boolean(activeBar instanceof HTMLElement && activeBar.classList.contains('opened'));
        }
    }, {
        key: 'activeBarId',
        get: function get() {
            var activeBar = this.activeBar;

            return activeBar instanceof HTMLElement && activeBar.getAttribute('data-pushbar-id');
        }
    }], [{
        key: 'dispatchOpen',
        value: function dispatchOpen(pushbar) {
            var event = new CustomEvent('pushbar_opening', {bubbles: true, detail: {pushbar: pushbar}});
            pushbar.dispatchEvent(event);
        }
    }, {
        key: 'dispatchClose',
        value: function dispatchClose(pushbar) {
            var event = new CustomEvent('pushbar_closing', {bubbles: true, detail: {pushbar: pushbar}});
            pushbar.dispatchEvent(event);
        }
    }, {
        key: 'findElementById',
        value: function findElementById(pushbarId) {
            return document.querySelector('[data-pushbar-id="' + pushbarId + '"]');
        }
    }]);

    return Pushbar;
}();