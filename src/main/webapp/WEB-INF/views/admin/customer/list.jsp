<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="customerListURL" value="/admin/customer-list"/>
<c:url var="customerAPI" value="/api/customer"/>
<html>
<head>
    <title>Danh sách khách hàng</title>
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
                    <li class="active">Quản lý khách hàng</li>
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
                <!-- /.page-header -->
                <div class="row">
                    <div class="col-xs-12">
                        <div class="widget-box ui-sortable-handle">
                            <div class="widget-header">
                                <h5 class="widget-title">Tìm kiếm</h5>

                                <div class="widget-toolbar">
                                    <a href="#" data-action="collapse">
                                        <i class="ace-icon fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>

                            <div
                                    class="widget-body"
                                    style="font-family: 'Times New Roman', Times, serif"
                            >
                                <div class="widget-main">
                                    <form:form id="listForm" modelAttribute="modelSearch" action="${customerListURL}"
                                               method="GET">
                                        <div class="row">
                                            <div>
                                                <div class="form-group">
                                                    <div class="col-xs-12">
                                                        <div class="col-xs-4">
                                                            <label class="name">Tên khách hàng</label>
                                                            <form:input class="form-control" path="customerName"/>
                                                        </div>
                                                        <div class="col-xs-4">
                                                            <label class="name">Di động</label>
                                                            <form:input class="form-control"
                                                                        path="customerPhoneNumber"/>
                                                        </div>
                                                        <div class="col-xs-4">
                                                            <label class="name">Email</label>
                                                            <form:input class="form-control" path="customerEmail"/>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-xs-12">
                                                    <security:authorize access="hasRole('MANAGER')">
                                                        <div class="col-xs-4">
                                                            <label class="name"
                                                            >Chọn nhân viên phụ trách</label
                                                            >
                                                            <form:select class="form-control" path="staffId">
                                                                <form:option
                                                                        value="">---Chọn nhân viên---</form:option>
                                                                <form:options items="${staffList}"/>
                                                            </form:select>
                                                        </div>
                                                    </security:authorize>
                                                </div>

                                                <div class="col-xs-12">
                                                    <div class="col-xs-6" style="margin-top: 20px;">
                                                        <button type="button" class="btn btn-xs btn-danger"
                                                                id="btnSearchCustomer">
                                                            <svg
                                                                    xmlns="http://www.w3.org/2000/svg"
                                                                    width="16"
                                                                    height="16"
                                                                    fill="currentColor"
                                                                    class="bi bi-search"
                                                                    viewBox="0 0 16 16"
                                                            >
                                                                <path
                                                                        d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"
                                                                ></path>
                                                            </svg>
                                                            Tìm kiếm
                                                        </button>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form:form>

                                </div>
                            </div>

                            <div class="pull-right">
                                <a href="/admin/customer-edit">
                                    <security:authorize access="hasRole('MANAGER')">
                                        <button class="btn btn-info" title="Thêm khách hàng">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                 fill="currentColor" class="bi bi-person-fill-add" viewBox="0 0 16 16">
                                                <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0m-2-6a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
                                                <path d="M2 13c0 1 1 1 1 1h5.256A4.5 4.5 0 0 1 8 12.5a4.5 4.5 0 0 1 1.544-3.393Q8.844 9.002 8 9c-5 0-6 3-6 4"/>
                                            </svg>
                                        </button>
                                    </security:authorize>
                                </a>
                                <security:authorize access="hasRole('MANAGER')">
                                    <button class="btn btn-danger" title="Xóa khách hàng" id="btnDeleteCustomer">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                             fill="currentColor" class="bi bi-person-x-fill" viewBox="0 0 16 16">
                                            <path fill-rule="evenodd"
                                                  d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6m6.146-2.854a.5.5 0 0 1 .708 0L14 6.293l1.146-1.147a.5.5 0 0 1 .708.708L14.707 7l1.147 1.146a.5.5 0 0 1-.708.708L14 7.707l-1.146 1.147a.5.5 0 0 1-.708-.708L13.293 7l-1.147-1.146a.5.5 0 0 1 0-.708"/>
                                        </svg>
                                    </button>
                                </security:authorize>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Bảng danh sách -->
                <div class="row">
                    <div class="col-xs-12">
                        <table
                                id="tableList"
                                style="margin: 3em 0 1.5em"
                                class="table table-striped table-bordered table-hover"
                        >
                            <thead>
                            <tr>
                                <th class="center">
                                    <label class="pos-rel">
                                        <input type="checkbox" name="checkList" value="" class="ace"/>
                                        <span class="lbl"></span>
                                    </label>
                                </th>
                                <th>Tên khách hàng</th>
                                <th>Di động</th>
                                <th>Email</th>
                                <th>Nhu cầu</th>
                                <th>Người thêm</th>
                                <th>Ngày thêm</th>
                                <th>Tình trạng</th>
                                <th>Thao tác</th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach var="item" items="${customerList}">
                                <tr>
                                    <td class="center">
                                        <label class="pos-rel">
                                            <input type="checkbox" class="ace" name="checkList" value="${item.id}"/>
                                            <span class="lbl"></span>
                                        </label>
                                    </td>

                                    <td>${item.fullname}</td>
                                    <td>${item.phone}</td>
                                    <td>${item.email}</td>
                                    <td>${item.demand}</td>
                                    <td>${item.createdBy}</td>
                                    <td>${item.createDate}</td>
                                    <td>${item.status}</td>
                                    <td>
                                        <div class="hidden-sm hidden-xs btn-group">
                                            <security:authorize access="hasRole('MANAGER')">
                                                <button
                                                        class="btn btn-xs btn-success"
                                                        title="Giao khách hàng"
                                                        onclick="assignmentCustomer(${item.id})"
                                                >
                                                    <i
                                                            class="icon-only ace-icon fa fa-align-justify"
                                                    ></i>
                                                </button>
                                            </security:authorize>

                                            <a class="btn btn-xs btn-info" title="Sửa thông tin khách hàng"
                                               href="/admin/customer-edit-${item.id}">
                                                <i class="ace-icon fa fa-pencil bigger-120"></i>
                                            </a>

                                            <security:authorize access="hasRole('MANAGER')">
                                                <button class="btn btn-xs btn-danger" title="Xóa khách hàng"
                                                        onclick="deleteCustomer(${item.id})">
                                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                </button>
                                            </security:authorize>
                                        </div>

                                        <div class="hidden-md hidden-lg">
                                            <div class="inline pos-rel">
                                                <button
                                                        class="btn btn-minier btn-primary dropdown-toggle"
                                                        data-toggle="dropdown"
                                                        data-position="auto"
                                                >
                                                    <i
                                                            class="ace-icon fa fa-cog icon-only bigger-110"
                                                    ></i>
                                                </button>

                                                <ul
                                                        class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close"
                                                >
                                                    <li>
                                                        <a
                                                                href="#"
                                                                class="tooltip-info"
                                                                data-rel="tooltip"
                                                                title=""
                                                                data-original-title="View"
                                                        >
                                  <span class="blue">
                                    <i
                                            class="ace-icon fa fa-search-plus bigger-120"
                                    ></i>
                                  </span>
                                                        </a>
                                                    </li>

                                                    <li>
                                                        <a
                                                                href="#"
                                                                class="tooltip-success"
                                                                data-rel="tooltip"
                                                                title=""
                                                                data-original-title="Edit"
                                                        >
                                  <span class="green">
                                    <i
                                            class="ace-icon fa fa-pencil-square-o bigger-120"
                                    ></i>
                                  </span>
                                                        </a>
                                                    </li>

                                                    <li>
                                                        <a
                                                                href="#"
                                                                class="tooltip-error"
                                                                data-rel="tooltip"
                                                                title=""
                                                                data-original-title="Delete"
                                                        >
                                  <span class="red">
                                    <i
                                            class="ace-icon fa fa-trash-o bigger-120"
                                    ></i>
                                  </span>
                                                        </a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.span -->
                </div>
            </div>
            <!-- /.page-content -->
        </div>
    </div>
    <!-- /.main-content -->
    <div
            class="modal fade"
            id="assignmentCustomerModal"
            role="dialog"
            ,
            style="font-family: 'Times New Roman', Times, serif"
    >
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                    </button>
                    <h4 class="modal-title">Danh sách nhân viên</h4>
                </div>
                <div class="modal-body">
                    <table
                            class="table table-striped table-bordered table-hover"
                            id="staffList"
                    >
                        <thead>
                        <tr>
                            <th>Chọn</th>
                            <th>Tên nhân viên</th>
                        </tr>
                        </thead>

                        <tbody>
                        </tbody>
                    </table>
                    <input type="hidden" , id="customerId" name="Customer" value="">
                </div>
                <div class="modal-footer">
                    <button
                            type="button"
                            class="btn btn-primary"
                            id="btnassignmentCustomer"
                    >
                        Giao khách hàng
                    </button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">
                        Đóng
                    </button>
                </div>
            </div>
        </div>
    </div>
    <script>
        function assignmentCustomer(customerId) {
            $('#assignmentCustomerModal').modal();
            loadStaff(customerId);
            $('#customerId').val(customerId);
        }

        function loadStaff(customerId) {
            $.ajax({
                type: "GET",
                url: '${customerAPI}/' + customerId + '/staffs',
                // data: JSON.stringify(data),
                contentType: "application/json",
                // dataType: "json",
                success: function (response) {
                    var row = '';
                    $.each(response.data, function (index, item) {
                        row += '<tr>';
                        row += '<td class="text-center"><input type="checkbox" value=' + item.staffId
                            + ' id=checkbox_' + item.staffId + ' class = "check-box-element"' + item.checked + '/></td>';
                        row += '<td class="text-center">' + item.fullName + '</td>';
                        row += '</tr>';
                    });
                    $('#staffList tbody').html(row);
                    console.info("Success!");
                },
                error: function (response) {
                    console.log("Failed!");
                    window.location.href = "<c:url value="/admin/customer-list?message=error"/>";
                    console.log(response);
                }
            });
        };

        $('#btnassignmentCustomer').click(function (e) {
            e.preventDefault();
            var data = {};
            data['customerId'] = $('#customerId').val();
            var staffs = $('#staffList').find('tbody input[type = checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            data['staffs'] = staffs;
            assignment(data);
            console.log("ok");
        });

        function assignment(data) {
            $.ajax({
                url: "${customerAPI}/" + 'assignment',
                type: "POST",
                data: JSON.stringify(data),
                contentType: "application/json",
                success: function (response) {
                    alert("Success!")
                },
                error: function (response) {
                    console.info("Giao Không Thành Cong!")
                    window.location.href = "<c:url value="/admin/customer-list?message=error"/>";
                    console.log(response);
                }
            });
        }

        $('#btnSearchCustomer').click(function (e) {
            e.preventDefault();
            $('#listForm').submit();
        });

        function deleteCustomer(data) {
            var customerId = [data];
            deleteCustomers(customerId);
        }

        $('#btnDeleteCustomer').click(function (e) {
            e.preventDefault();
            var customerIds = $('#tableList').find('tbody input[type = checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            deleteCustomers(customerIds);
        });

        function deleteCustomers(data) {
            $.ajax({
                type: "Delete",
                url: "${customerAPI}/" + data,
                data: JSON.stringify(data),
                contentType: "application/json",
                success: function (response) {
                    alert("Deleted successfully!")
                    console.log(response)
                    window.location.href = "<c:url value="/admin/customer-list"/>";
                },
                error: function (response) {
                    alert('Failed!');
                    console.log(response);
                }
            });
        };

        document.addEventListener("DOMContentLoaded", function () {
            const masterCheckbox = document.querySelector('thead input[name="checkList"]');
            const rowCheckboxes = document.querySelectorAll('tbody input[name="checkList"]');

            masterCheckbox.addEventListener('change', function () {
                rowCheckboxes.forEach(checkbox => {
                    checkbox.checked = masterCheckbox.checked;
                });
            });

            rowCheckboxes.forEach(checkbox => {
                checkbox.addEventListener('change', function () {
                    masterCheckbox.checked = Array.from(rowCheckboxes).every(chk => chk.checked);
                });
            });
        });
    </script>
</div>
</body>
</html>
