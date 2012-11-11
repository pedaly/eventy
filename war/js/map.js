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

		var city = $("#addEditForm #city").val();
		var street = $("#addEditForm #streetAndNumber").val();
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
							for ( var i = 0; i < msg.results.length; i++) {

								for ( var j = 0; j < msg.results[i].locations.length; j++) {

									if (msg.results[i].locations.length < 2) {

										newPoi = new MQA.Poi(
												{
													lat : msg.results[i].locations[j].displayLatLng.lat,
													lng : msg.results[i].locations[j].displayLatLng.lng,
												});
										newPoi.draggable = false;

										this.eventPoint = newPoi;
										EventMap.map.addShape(this.eventPoint);
										EventMap.map.bestFit();
										fillLanLngLabel(newPoi.getLatLng().lat,
												newPoi.getLatLng().lng);
										EventMap.isEmpty = false;
									} else {
										alert("Sory gosciu ale adres jest ma³o specyzyjny");
									}

								}
							}
						});

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
}
function fillLanLngLabel(lat, lng) {
	$("#addEditForm #lat").val(lat);
	$("#addEditForm #long").val(lng);

}
