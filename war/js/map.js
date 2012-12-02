EventMap = {
	height : 0,
	width : 0,
	defaultLon : 19.47,
	defaultLat : 51.76,
	defaultZoom : 9,
	defaultMapType : 'map',
	map : {},
	eventPoint : {},
	isEmpty : true,
	zoomOnDoubleClick : false,
	useRightClick : true,
	vZoomControl : true,
	vViewOptions : true,
	vMiniMap : false,
	resp : {},

	init : function(w, h) {
		this.height = h;
		this.width = w;
		$("#map").height(this.height);
		$("#map").width(this.width);
		$(".push_frame").height(0);

		var options = {
			elt : document.getElementById('map'),
			zoom : this.defaultZoom,
			latLng : {
				lat : this.defaultLat,
				lng : this.defaultLon
			},
			mtype : 'map',
			bestFitMargin : 0,
			zoomOnDoubleClick : true

		};

		this.map = new MQA.TileMap(options);
		MQA.withModule('largezoom', function() {
			EventMap.map.addControl(new MQA.LargeZoom(),
					new MQA.MapCornerPlacement(MQA.MapCorner.TOP_LEFT,
							new MQA.Size(5, 5)));
		});
		MQA.withModule('mousewheel', function() {
			EventMap.map.enableMouseWheelZoom();
		});
		MQA.withModule('viewoptions', function() {
			EventMap.map.addControl(new MQA.ViewOptions());
		});
		MQA.withModule('traffictoggle', function() {
			EventMap.map.addControl(new MQA.TrafficToggle());

		});

	},

	addPoint : function(event) {

		if (EventMap.isEmpty == true) {
			var myPoi = new MQA.Poi();
			myPoi.draggable = true;
			myPoi.setLatLng(event.ll);

			MQA.EventManager.addListener(myPoi, 'click', EventMap.removePoint);
			EventMap.addDragClickListener(myPoi);
			fillLanLngLabel(myPoi.getLatLng().lat, myPoi.getLatLng().lng);

			this.eventPoint = myPoi;
			EventMap.map.addShape(this.eventPoint);
			EventMap.isEmpty = false;
		}

	},
	addPointByAddress : function() {
		EventMap.clearMap();
		var cityOrigin = $("#addEditForm #city").val();
		if ($("#addEditForm #city").val() != undefined)
			var city = EventMap.removePolishChar(cityOrigin);
		else
			var city = [];
		
		var streetOrigin = $("#addEditForm #streetAndNumber").val();
		if ($("#addEditForm #streetAndNumber").val() != undefined)
			var street = EventMap.removePolishChar(streetOrigin);
		else
			var street = [];

		// var city = $("#addEditForm #city").val();
		// var street = $("#addEditForm #streetAndNumber").val();
		var newPoi;
		$
				.ajax({
					type : "GET",
					dataType : 'jsonp',
					crossDomain : true,
					url : 'http://www.mapquestapi.com/geocoding/v1/address',
					data : {
						key : "Fmjtd|luua25ub20%2C72%3Do5-962xhz",
						street : street,
						city : city,
						adminArea1 : "PL",
						inFormat : 'json'
					}
				})
				.done(
						function(msg) {
							EventMap.resp = msg;
							if (msg.results[0].locations.length < 2) {
								for ( var i = 0; i < msg.results.length; i++) {
									for ( var j = 0; j < msg.results[i].locations.length; j++) {
										newPoi = new MQA.Poi(
												{
													lat : msg.results[i].locations[j].displayLatLng.lat,
													lng : msg.results[i].locations[j].displayLatLng.lng,
												});
										newPoi.draggable = true;

										this.eventPoint = newPoi;
										EventMap.map.addShape(this.eventPoint);
										EventMap.map.bestFit();
										
										MQA.EventManager.addListener(this.eventPoint, 'click', EventMap.removePoint);
										EventMap.addDragClickListener(this.eventPoint);
										
										fillLanLngLabel(newPoi.getLatLng().lat,
												newPoi.getLatLng().lng);
										EventMap.isEmpty = false;

									}
								}
							} else {
								alert("Sory gościu ale adres jest mało specyzyjny - znaleziono więcej niż 1 wynik...");
							}
						});

	},
	clearMap : function() {
		EventMap.map.removeShape(this.eventPoint);
		EventMap.map.removeAllShapes();
	},
	removePoint : function(MouseEvent) {
		if (MQA.EventUtil.isRightClick(MouseEvent)) {
			EventMap.map.removeShape(this);
			EventMap.isEmpty = true;

		}

	},
	addDragClickListener : function(myPoi) {
		MQA.EventManager.addListener(myPoi, 'drag', function(evt) {
			this.eventPoint = myPoi;
			fillLanLngLabel(this.eventPoint.getLatLng().lat, this.eventPoint
					.getLatLng().lng);
			EventMap.map.addShape(this.eventPoint);
		});

	},
	removePolishChar : function(address) {
		var addr = [];
		addr = address;
		alert(address);
		for ( var i = 0; i < addr.length; i++) {
			switch (addr[i]) {
			case "ą":
				addr = addr.substr(0, i) + 'a' + addr.substr(i + 1);
				break;
			case "ć":
				addr = addr.substr(0, i) + 'c' + addr.substr(i + 1);
				break;
			case "ę":
				addr = addr.substr(0, i) + 'e' + addr.substr(i + 1);
				break;

			case "ł":
				addr = addr.substr(0, i) + 'l' + addr.substr(i + 1);
				break;
			case "ń":
				addr = addr.substr(0, i) + 'n' + addr.substr(i + 1);
				break;
			case "ó":
				addr = addr.substr(0, i) + 'o' + addr.substr(i + 1);
				break;
			case "ś":
				addr = addr.substr(0, i) + 's' + addr.substr(i + 1);
				break;
			case "ź":
				addr = addr.substr(0, i) + 'z' + addr.substr(i + 1);
				break;
			case "ż":
				addr = addr.substr(0, i) + 'z' + addr.substr(i + 1);
				break;
			case "Ą":
				addr = addr.substr(0, i) + 'a' + addr.substr(i + 1);
				break;
			case "Ć":
				addr = addr.substr(0, i) + 'c' + addr.substr(i + 1);
				break;
			case "Ę":
				addr = addr.substr(0, i) + 'e' + addr.substr(i + 1);
				break;
			case "Ł":
				addr = addr.substr(0, i) + 'l' + addr.substr(i + 1);
				break;
			case "Ń":
				addr = addr.substr(0, i) + 'n' + addr.substr(i + 1);
				break;
			case "Ó":
				addr = addr.substr(0, i) + 'o' + addr.substr(i + 1);
				break;
			case "Ś":
				addr = addr.substr(0, i) + 's' + addr.substr(i + 1);
				break;
			case "Ź":
				addr = addr.substr(0, i) + 'z' + addr.substr(i + 1);
				break;
			case "Ż":
				addr = addr.substr(0, i) + 'z' + addr.substr(i + 1);
				break;
			}
		}

		return addr;

	}
}
function fillLanLngLabel(lat, lng) {
	$("#addEditForm #lat").val(lat);
	$("#addEditForm #long").val(lng);

}
