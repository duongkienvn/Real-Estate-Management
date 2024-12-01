<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="customerAPI" value="/api/customer"/>
<html>
<head>
    <title>Thêm hoặc sửa khách hàng</title>
</head>
<body>
<div class="main-content" id="main-container">
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try {
                        ace.settings.check("breadcrumbs", "fixed");
                    } catch (e) {
                    }
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#">Home</a>
                    </li>
                    <li class="active">Thêm khách hàng</li>
                </ul>
                <!-- /.breadcrumb -->
            </div>

            <div class="page-content">
                <div class="ace-settings-container" id="ace-settings-container">
                    <div
                            class="btn btn-app btn-xs btn-warning ace-settings-btn"
                            id="ace-settings-btn"
                    >
                        <i class="ace-icon fa fa-cog bigger-130"></i>
                    </div>

                    <div class="ace-settings-box clearfix" id="ace-settings-box">
                        <div class="pull-left width-50">
                            <div class="ace-settings-item">
                                <div class="pull-left">
                                    <select id="skin-colorpicker" class="hide">
                                        <option data-skin="no-skin" value="#438EB9">
                                            #438EB9
                                        </option>
                                        <option data-skin="skin-1" value="#222A2D">
                                            #222A2D
                                        </option>
                                        <option data-skin="skin-2" value="#C6487E">
                                            #C6487E
                                        </option>
                                        <option data-skin="skin-3" value="#D0D0D0">
                                            #D0D0D0
                                        </option>
                                    </select>
                                </div>
                                <span>&nbsp; Choose Skin</span>
                            </div>

                            <div class="ace-settings-item">
                                <input
                                        type="checkbox"
                                        class="ace ace-checkbox-2"
                                        id="ace-settings-navbar"
                                />
                                <label class="lbl" for="ace-settings-navbar">
                                    Fixed Navbar</label
                                >
                            </div>

                            <div class="ace-settings-item">
                                <input
                                        type="checkbox"
                                        class="ace ace-checkbox-2"
                                        id="ace-settings-sidebar"
                                />
                                <label class="lbl" for="ace-settings-sidebar">
                                    Fixed Sidebar</label
                                >
                            </div>

                            <div class="ace-settings-item">
                                <input
                                        type="checkbox"
                                        class="ace ace-checkbox-2"
                                        id="ace-settings-breadcrumbs"
                                />
                                <label class="lbl" for="ace-settings-breadcrumbs">
                                    Fixed Breadcrumbs</label
                                >
                            </div>

                            <div class="ace-settings-item">
                                <input
                                        type="checkbox"
                                        class="ace ace-checkbox-2"
                                        id="ace-settings-rtl"
                                />
                                <label class="lbl" for="ace-settings-rtl">
                                    Right To Left (rtl)</label
                                >
                            </div>

                            <div class="ace-settings-item">
                                <input
                                        type="checkbox"
                                        class="ace ace-checkbox-2"
                                        id="ace-settings-add-container"
                                />
                                <label class="lbl" for="ace-settings-add-container">
                                    Inside
                                    <b>.container</b>
                                </label>
                            </div>
                        </div>
                        <!-- /.pull-left -->

                        <div class="pull-left width-50">
                            <div class="ace-settings-item">
                                <input
                                        type="checkbox"
                                        class="ace ace-checkbox-2"
                                        id="ace-settings-hover"
                                />
                                <label class="lbl" for="ace-settings-hover">
                                    Submenu on Hover</label
                                >
                            </div>

                            <div class="ace-settings-item">
                                <input
                                        type="checkbox"
                                        class="ace ace-checkbox-2"
                                        id="ace-settings-compact"
                                />
                                <label class="lbl" for="ace-settings-compact">
                                    Compact Sidebar</label
                                >
                            </div>

                            <div class="ace-settings-item">
                                <input
                                        type="checkbox"
                                        class="ace ace-checkbox-2"
                                        id="ace-settings-highlight"
                                />
                                <label class="lbl" for="ace-settings-highlight">
                                    Alt. Active Item</label
                                >
                            </div>
                        </div>
                        <!-- /.pull-left -->
                    </div>
                    <!-- /.ace-settings-box -->
                </div>
                <!-- /.ace-settings-container -->

                <div class="page-header">
                    <h1 style="font-family: 'Times New Roman', Times, serif">
                        Thông tin khách hàng
                    </h1>
                </div>
                <!-- /.page-header -->

                <div class="row">
                    <div class="col-xs-12"></div>
                </div>
                <!-- Bảng danh sách -->
                <div
                        class="row"
                        style="font-family: 'Times New Roman', Times, serif"
                >
                    <form:form modelAttribute="customerEdit" id="listForm" method="GET">
                        <div class="col-xs-12">
                            <form class="form-horizontal" role="form">
                                <div class="form-group">
                                    <label class="col-xs-3">Tên khách hàng</label>
                                    <div class="col-xs-9">
                                        <form:input
                                                class="form-control" path="fullname"
                                        />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Số điện thoại</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="phone"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Email</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="email"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Tên công ty</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="companyName"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Nhu cầu</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="demand"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Tình trạng</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="status"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3"></label>
                                    <div class="col-xs-9">
                                        <c:if test="${not empty customerEdit.id}">
                                            <button type="button" class="btn btn-primary" id="btnAddOrUpdateCustomer">
                                                Cập nhật thông tin khách hàng
                                            </button>
                                            <button type="button" class="btn btn-primary" id="btnCancel">Hủy thao tác
                                            </button>
                                        </c:if>

                                        <c:if test="${empty customerEdit.id}">
                                            <button type="button" class="btn btn-primary" id="btnAddOrUpdateCustomer">
                                                Thêm khách hàng
                                            </button>
                                            <button type="button" class="btn btn-primary" id="btnCancel">Hủy thao tác
                                            </button>
                                        </c:if>
                                    </div>
                                </div>
                                <form:hidden path="id" id="customerId"/>
                            </form>
                        </div>
                    </form:form>
                </div>

                <c:if test="${not empty customerEdit.id}">
                    
                </c:if>
            </div>
            <!-- /.page-content -->
        </div>
    </div>
    <!-- /.main-content -->
    <script>
        $('#btnAddOrUpdateCustomer').click(function () {
            var data = {};
            var formData = $('#listForm').serializeArray();
            $.each(formData, function (i, v) {
                data["" + v.name + ""] = v.value;
            });
            if (data['name'] != '' && data['phone'] != '') {
                addOrUpdateCustomer(data);
            } else {
                alert("Name and phone must not blank!")
                window.location.href = "<c:url value="/admin/customer-edit"/>";
            }
        });

        function addOrUpdateCustomer(data) {
            $.ajax({
                url: "${customerAPI}",
                type: "POST",
                data: JSON.stringify(data),
                contentType: "application/json",
                success: function (response) {
                    alert("Success!")
                    console.log(response)
                },
                error: function (response) {
                    alert("failed!")
                    console.log(response)
                }
            });
        };

        $('#btnCancel').click(function () {
            window.location.href = "/admin/customer-list";
        })
    </script>
</div>
</body>
</html>
