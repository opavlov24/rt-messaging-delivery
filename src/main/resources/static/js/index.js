class WebSocketController {

  constructor() {
    this._onConnected = this._onConnected.bind(this);
  }

  _onConnected(frame) {
    this.stompClient.subscribe('/topic/dummies', this.showMessage);
  }

  connect() {
    var socket = new SockJS('/websocket');
    this.stompClient = Stomp.over(socket);
    this.stompClient.connect({}, this._onConnected);
  }

  showMessage(message) {
    var response = document.getElementById('response');
    var p = document.createElement('p');
    p.style.wordWrap = 'break-word';
    p.appendChild(document.createTextNode(message.body));
    response.appendChild(p);
  }

}
var webSocket = new WebSocketController();