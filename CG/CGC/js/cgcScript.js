
function weight(len, ctx){
	ctx.fillStyle = '#727272';
	ctx.fillRect(50, 50, 150+len, 10);

	ctx.fillRect(60, 40, 10, 30);
	ctx.fillRect(180+len, 40, 10, 30);

	ctx.fillRect(75, 30, 10, 50);
	ctx.fillRect(165+len, 30, 10, 50);
}

function tapete_rolante(ctx){
	ctx.fillStyle = '#5c5c5c';
	ctx.translate(480, 260);
	ctx.rotate(-90);
	ctx.fillRect(-5, -10, 140, 10);
	ctx.fillRect(120, -30, 15, 50);

	ctx.rotate(90);
	ctx.translate(-480, -260);
	ctx.fillStyle = '#272727';
	ctx.fillRect(300, 260, 180, 10);

	ctx.beginPath();
	ctx.arc(300, 265, 4.5, 0, Math.PI*2, true);
	ctx.fill();
	ctx.moveTo(480, 265);
	ctx.arc(480, 265, 4.5, 0, Math.PI*2, true);
	ctx.fill();

	ctx.stroke();
}

function rack(ctx){
	ctx.beginPath();
	ctx.moveTo(45, 275);
	ctx.lineWidth = 10;
	ctx.lineTo(75, 105);
	ctx.moveTo(175, 275);
	ctx.lineTo(145, 105);
	ctx.fill();
	ctx.stroke();
	ctx.lineWidth = 1;

	ctx.fillStyle = '#ce2900';
	ctx.fillRect(40, 270, 140, 10);
	
}

function background(ctx){
	//PAREDE E CHAO
	ctx.fillStyle = '#ffae31';
	ctx.fillRect(0, 0, 500, 250);
	ctx.fillStyle = '#c9c9c9';
	ctx.fillRect(0, 250, 500, 550);


	tapete_rolante(ctx);
	rack(ctx);

	ctx.translate(-15,80);
	weight(0, ctx);

	ctx.translate(-30, 75);
	weight(60, ctx);
	ctx.fillRect(90, 20, 10, 70);
	ctx.fillRect(210, 20, 10, 70);

	ctx.translate(45, -155);
}

function torso(ctx){
	ctx.beginPath();
	ctx.moveTo(200, 210);
	ctx.bezierCurveTo(215, 150, 250, 150, 265, 210);
	ctx.lineTo(200, 210);
	ctx.fillStyle = '#3341ff';
	ctx.fill();

	ctx.beginPath();
	ctx.moveTo(200, 210);
	ctx.lineTo(180, 300);
	ctx.bezierCurveTo(215, 325 ,250 ,325, 285, 300);
	ctx.lineTo(265, 210);
	ctx.fillStyle = 'red';
	ctx.fill();

}

function leg(x, ctx){
	ctx.beginPath();
	ctx.moveTo(0*x,0);
	ctx.lineTo(50*x, 0);
	ctx.lineTo(50*x, 25);
	ctx.bezierCurveTo(50*x, 33, 0*x, 31, 0*x, 0);
	ctx.fillStyle = '#3ea3ff';
	ctx.fill();


	ctx.beginPath();
	ctx.moveTo(50*x, 25);
	ctx.bezierCurveTo(65*x, 125, 41*x, 100, 75*x, 120);
	ctx.lineTo(50*x, 160);
	ctx.bezierCurveTo(0*x, 130, 25*x, 65, 30*x, 28);
	ctx.fillStyle = 'yellow';
	ctx.fill();

	ctx.beginPath();
	ctx.moveTo(75*x, 120);
	ctx.bezierCurveTo(180*x, 160, 100*x, 200, 50*x, 160);
	ctx.lineTo(75*x, 120);
	ctx.fillStyle = '#3341ff';
	ctx.fill();
}

function head(ctx){
	//NECK
	ctx.beginPath();
	ctx.moveTo(222, 318);
	ctx.bezierCurveTo(222, 305, 242, 305, 242, 318);
	ctx.lineTo(242, 328);
	ctx.lineTo(222, 328);
	ctx.lineTo(222, 318);
	ctx.fillStyle = 'yellow';
	ctx.fill();

	//CABEÇA
	ctx.beginPath();
	ctx.arc(232, 360, 35, 0, 2*Math.PI);
	ctx.fillStyle = 'yellow';
	ctx.fill();

	//NARIZ
	ctx.beginPath();
	ctx.ellipse(232, 354, 4, 2, 0, 0, 2*Math.PI);
	ctx.fillStyle = 'orange';
	ctx.fill();

	//BOCA
	ctx.beginPath();
	ctx.moveTo(220, 344);
	ctx.bezierCurveTo(220, 340, 244, 340, 244, 344);
	ctx.bezierCurveTo(244, 330, 220, 330, 220, 344);
	ctx.fillStyle = 'red';
	ctx.fill();

	//OLHOS
	ctx.beginPath();
	ctx.arc(218, 365, 5, 0, 2*Math.PI);
	ctx.arc(246, 365, 5, 0, 2*Math.PI);
	ctx.fillStyle = 'black';
	ctx.fill();

	//ORELHAS
	ctx.beginPath();
	ctx.arc(197, 360, 7, 0, 2*Math.PI);
	ctx.arc(267, 360, 7, 0, 2*Math.PI);
	ctx.fillStyle = 'yellow';
	ctx.fill();

	//CABELO
	ctx.beginPath();
	ctx.moveTo(269, 355);
	ctx.lineTo(269, 365);
	ctx.arc(232, 365, 37, 0, Math.PI);
	ctx.lineTo(199, 355);
	ctx.lineTo(201, 368);
	ctx.quadraticCurveTo(210, 380, 220, 380);
	ctx.quadraticCurveTo(250, 380, 269, 355);
	ctx.fillStyle = 'brown';
	ctx.fill();
}	

function arm(x, ctx){
	ctx.beginPath();
	ctx.fillRect(0*x, 0, 10*x, 10);
	ctx.moveTo(20*x, 7);
	ctx.bezierCurveTo(10*x, -5, -35*x, -20, -1*x, 30);
	ctx.lineTo(20*x, 7);
	ctx.fillStyle = 'red';
	ctx.fill();

	ctx.beginPath();
	ctx.moveTo(20*x, 7);
	ctx.bezierCurveTo(75*x, 60, 30*x, 70, -1*x, 30);
	ctx.fillStyle = 'yellow';
	ctx.fill();
}

function fore_arm(x, ctx){
	ctx.beginPath();

	ctx.moveTo(50*x, 40);
	ctx.bezierCurveTo(35*x, 10, 20*x, 35, 25*x, 55);
	ctx.bezierCurveTo(35*x, 110, 70*x, 110, 50*x, 40);
	ctx.fillStyle = 'yellow';
	ctx.fill();

	ctx.moveTo(50*x, 100);



	//MAOS
	ctx.fillRect(50*x, 100, -10, -10);
	ctx.arc(50*x, 100, 15, 0, 2*Math.PI);
	ctx.fill();
}


function person(ctx){
	ctx.translate(440, 480);

	//PERNAS
	ctx.rotate(Math.PI);
	leg(1, ctx);
	ctx.translate(200, 0);
	leg(-1, ctx);

	//TORSO
	ctx.translate(-332, -35);
	torso(ctx);

	//CABEÇA
	head(ctx);
}



function bracos(ctx){
	ctx.translate(40, -10);
	arm(1, ctx);
	
	ctx.translate(10, 10);
	fore_arm(1, ctx);

	
	ctx.translate(-90, -10);
	arm(-1, ctx);
	ctx.translate(-10, 10);
	fore_arm(-1, ctx);
}




function main() {
	var canvas = document.getElementById('canvas');
	var ctx = canvas.getContext('2d');
	background(ctx);
	person(ctx);

	ctx.translate(232, 300);

    let b = new bracos(ctx);

    ctx.translate(-155, 50);
    let p = new weight(160, ctx);



/*
    function animate(){
    	requestAnimationFrame(animate);
    	b.move();
    	p.move();
    	ctx.clearRect(0, 0, 100, 100);
    }
    animate();
*/
}