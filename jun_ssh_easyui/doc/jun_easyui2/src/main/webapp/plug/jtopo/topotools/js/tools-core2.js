var firstX = 15;
var firstY = 15;

function createCenter(json) {
    renderANode(json);
}

/*core*/
function render(node) {
    node.fontColor = '20,10,20';
    node._id = Math.uuid();
    var str=node._id;
	var indexStr1=parseInt(str.lastIndexOf("-"))+1;
    node.cNodeId=str.substring(indexStr1);
    initARightButton(node);
    scene.add(node);
}

function renderNodeToCenter(id) {
    var json = RENDER_MAP.get(id);
    document.getElementById('canvasFram').contentWindow.createCenter(json);
}

function createCommonNode(nodeName, id, imagePath) {
    var node = new JTopo.Node(nodeName);
    node._id = id;
    node.setSize(15, 19);
    //node.fontColor = '0,0,0';
    node.fontColor = '41,36,33';
    node.dragable = false;
    node.mode = 'select';
    node.setImage(imagePath, false);
    node.dbclick(function (e1) {
        renderNodeToCenter(node._id);
    });
    return node;
}

function createModel(jsonNode) {
    var node = this.createCommonNode(jsonNode.c_name, jsonNode.c_Id, jsonNode.c_imagePath);
    node.setSize(jsonNode.c_sizeW, jsonNode.c_sizeH);
    return node;
}

function renderANode(json) {
    var node;
    if(json.nodeElementType=="node"){
        node = new JTopo.Node(json.d_name);
        node.c_Id=json.c_Id;
        node.setImage(json.c_imagePath, false);
        node.setSize(json.d_sizeW, json.d_sizeH);
    }else if(json.nodeElementType=="textNode"){
        node = new JTopo.TextNode(json.d_name);
    }
    node.setLocation(firstX, firstY);
    render(node);
}











