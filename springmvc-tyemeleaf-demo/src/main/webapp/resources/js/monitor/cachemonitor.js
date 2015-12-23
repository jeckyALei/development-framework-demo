(function() {
	var MonitorModel = Backbone.Model.extend();
	var MonitorModelList = Backbone.Collection.extend({
		model : MonitorModel,
		segmentNodes : function(cacheName) {
			var _this = this;
			this.type = "Segment Nodes";
			this.setSortName("segment");
			$.ajax({
				url : ctx + 'monitor/segmentNode',
				dataType : "json",
				data : {
					"cacheName" : cacheName
				},
				success : function(segmentNodes) {
					_this.reset(segmentNodes);
				}
			});

		},
		nodeSegments : function(cacheName) {
			var _this = this;
			this.type = "Node Segments";
			this.setSortName("node");
			$.ajax({
				url : ctx + 'monitor/jgroupNodeSegment',
				dataType : "json",
				data : {
					"cacheName" : cacheName
				},
				success : function(nodeSegments) {
					_this.reset(nodeSegments);
				}
			});

		},
		cacheStatistics : function(cacheName) {
			var _this = this;
			this.type = "Cache Statistics";
			$.ajax({
				url : ctx + 'monitor/cacheStatistics',
				data : {
					"cacheName" : cacheName
				},
				dataType : "json",
				success : function(cacheStatisticsList) {
					_this.reset(cacheStatisticsList);
				}
			});

		},
		cacheRpliStatistics : function(cacheName) {
			var _this = this;
			this.type = "Cache Replication StatisticsList";
			$.ajax({
				url : ctx + 'monitor/cacheRpliStatistics',
				data : {
					"cacheName" : cacheName
				},
				dataType : "json",
				success : function(cacheRpliStatisticsList) {
					_this.reset(cacheRpliStatisticsList);
				}
			});

		},
		clusterInfo : function() {
			var _this = this;
			this.type = "Cluster Info";
			$.ajax({
				url : ctx + 'monitor/clusterInfo',
				dataType : "json",
				success : function(clusterInfo) {
					_this.reset(clusterInfo);
				}
			});

		},
		hotrodServerInfo : function() {
			var _this = this;
			this.type = "Hotrod Server Info";
			$.ajax({
				url : ctx + 'monitor/hotrodServerInfo',
				dataType : "json",
				success : function(hotrodServerInfo) {
					_this.reset(hotrodServerInfo);
				}
			});

		},
		comparator : function(item) {
			var sortName = this.sortName;
			if (sortName) {
				if (sortName == "segment") {
					return parseInt(item.get(sortName));
				} else {
					return item.get(sortName);
				}
			}
		},
		setSortName : function(name) {
			this.sortName = name;
		}
	});
	var monitorModelList = new MonitorModelList();
	var MonitorTable = Backbone.View.extend({
		el : "#monitor_table",
		initialize : function() {
			var tmpl = $("#tableHeader").html().replace("<!--", "").replace("-->", "");
			$.templates({
				tableHeader : tmpl
			});
			tmpl = $("#segmentNode").html().replace("<!--", "").replace("-->", "");
			$.templates({
				segmentNode : tmpl
			});
			tmpl = $("#nodeSegments").html().replace("<!--", "").replace("-->", "");
			$.templates({
				nodeSegments : tmpl
			});
			tmpl = $("#tableBody").html().replace("<!--", "").replace("-->", "");
			$.templates({
				tableBody : tmpl
			});
			monitorModelList.on("reset", function() {
				if (monitorModelList.type == "Segment Nodes") {
					$(this.el).html($.render.tableHeader({
						headers : [ {
							name : "nodes"
						}, {
							name : "segment"
						} ]
					}));
					$(this.el).append($.render.segmentNode(monitorModelList.toJSON()));
				} else if (monitorModelList.type == "Node Segments") {
					$(this.el).html($.render.tableHeader({
						headers : [ {
							name : "node"
						}, {
							name : "segments"
						} ]
					}));
					$(this.el).append($.render.nodeSegments(monitorModelList.toJSON()));
				} else {
					$(this.el).html($.render.tableHeader({
						headers : [ {
							name : "name"
						}, {
							name : "value"
						} ]
					}));
					$(this.el).append($.render.tableBody(monitorModelList.toJSON()));
				}
				$(this.el).parent().find(" > h3").html(monitorModelList.type);

			}, this);
		}
	});

	var MonitorApp = Backbone.View.extend({
		el : "#wrap",
		initialize : function() {
			this.monitorTable = new MonitorTable(new MonitorModelList());
			this.initDhtmlxTree();
		},
		initDhtmlxTree : function() {
			this.dhtmlxTree = new dhtmlXTreeObject("monitor_tree", "100%", "100%", 0);
			this.dhtmlxTree.setSkin('dhx_terrace');
			this.dhtmlxTree.setImagePath(ctx + "resources/dhtmlx/imgs/");
			var _this = this;
			this.dhtmlxTree.attachEvent("onSelect", function(id) {
				if (!this.hasChildren(id)) {
					var type = this.getAttribute(id, "type");
					var cacheName = this.getItemText(id);
					eval("monitorModelList." + type + "('"+cacheName+"')");
					return true;
				}
				return false;
			});
			this.dhtmlxTree.attachEvent("onXLE", function(tree) {
				tree.selectItem(5);
			});
			$.ajax({
				url : ctx + 'monitor/tree',
				dataType : "json",
				success : function(cacheNamesJson) {
					_this.dhtmlxTree.loadJSONObject(cacheNamesJson);
				}
			});
		}
	});

	$(document.body).ready(function() {
		new MonitorApp();
	});
})();
