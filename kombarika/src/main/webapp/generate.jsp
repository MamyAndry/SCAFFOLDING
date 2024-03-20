<%@ page import="ambovombe.kombarika.generator.CodeGenerator" %>
<%@ page import="java.util.Map" %>
<%@ page import="ambovombe.kombarika.database.DbProperties" %>
<%@ page import="ambovombe.kombarika.configuration.mapping.ViewProperties" %><%--
  Created by IntelliJ IDEA.
  User: Fanilo
  Date: 18/03/2024
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    CodeGenerator codeGenerator = new CodeGenerator();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Kombarika</title>
    <!-- loader-->
    <link href="assets/css/pace.min.css" rel="stylesheet"/>
    <script src="assets/js/pace.min.js"></script>
    <!--favicon-->
    <link rel="icon" href="assets/images/favicon.ico" type="image/x-icon">
    <!-- simplebar CSS-->
    <link href="assets/plugins/simplebar/css/simplebar.css" rel="stylesheet"/>
    <!-- Bootstrap core CSS-->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet"/>
    <!-- animate CSS-->
    <link href="assets/css/animate.css" rel="stylesheet" type="text/css"/>
    <!-- Icons CSS-->
    <link href="assets/css/icons.css" rel="stylesheet" type="text/css"/>
    <!-- Sidebar CSS-->
    <link href="assets/css/sidebar-menu.css" rel="stylesheet"/>
    <!-- Custom Style-->
    <link href="assets/css/app-style.css" rel="stylesheet"/>


</head>

<body class="bg-theme bg-theme2">

<!-- Start wrapper-->
<div id="wrapper">

    <!--Start sidebar-wrapper-->
    <div id="sidebar-wrapper" data-simplebar="" data-simplebar-auto-hide="true">
        <div class="brand-logo">
            <a href="index.html">
                <img src="assets/images/logo-icon.png" class="logo-icon" alt="logo icon">
                <h5 class="logo-text">Ambovombe</h5>
            </a>
        </div>
        <ul class="sidebar-menu do-nicescrol">
            <li class="sidebar-header">MAIN NAVIGATION</li>
            <li>
                <a href="choixConnection.html">
                    <i class="zmdi zmdi-view-dashboard"></i> <span>Generate projects</span>
                </a>
            </li>

            <li>
                <a href="addConnection.html">
                    <i class="zmdi zmdi-format-list-bulleted"></i> <span>Add connections</span>
                </a>
            </li>
        </ul>

    </div>
    <!--End sidebar-wrapper-->


    <!--Start topbar header-->
    <header class="topbar-nav">
        <nav class="navbar navbar-expand fixed-top">
            <ul class="navbar-nav mr-auto align-items-center">
                <li class="nav-item">
                    <a class="nav-link toggle-menu" href="javascript:void();">
                        <i class="icon-menu menu-icon"></i>
                    </a>
                </li>
                <li class="nav-item">
                    <form class="search-bar">
                        <input type="text" class="form-control" placeholder="Enter keywords">
                        <a href="javascript:void();"><i class="icon-magnifier"></i></a>
                    </form>
                </li>
            </ul>
        </nav>
    </header>
    <!--End topbar header-->
    <div class="clearfix"></div>

    <div class="content-wrapper">
        <div class="container-fluid">

            <div class="row mt-3">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-body">
                            <div class="card-title">Generate project</div>
                            <hr>
                            <form method="post" action="generate">
                                <div class="row mt-3">
                                    <div class="col-lg-6">
                                        <div class="form-group">
                                            <label for="input-1">Language</label>
                                            <select class="form-control" id="input-1" name="framework">
                                                <option>Java:merana</option>
                                                <option>Java:spring</option>
                                                <option>Dotnet:entity</option>
                                                <option>Dotnet:mvc</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="input-3">View</label>
                                            <select class="form-control" name="viewType" id="input-3">
                                                <% for (Map.Entry<String, ViewProperties> set: codeGenerator.getViewDetails().getViews().entrySet()){ %>
                                                <option value="<%=set.getKey()%>"><%=set.getKey()%></option>
                                                <% } %>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="input-4">Package name</label>
                                            <input type="text" class="form-control" name="packageName" id="input-4">
                                        </div>
                                        <div class="form-group">
                                            <label for="input-5">Entity package</label>
                                            <input type="text" class="form-control" name="entity" id="input-5">
                                        </div>
                                        <div class="form-group">
                                            <label for="input-6">Controller package</label>
                                            <input type="text" class="form-control" name="controller" id="input-6">
                                        </div>
                                        <div class="form-group">
                                            <label for="input-8">Repository package</label>
                                            <input type="text" class="form-control" name="repository" id="input-8">
                                        </div>
                                        <div class="form-group">
                                            <label for="input-9">View package</label>
                                            <input type="text" class="form-control" name="view" id="input-9">
                                        </div>
                                        <div class="form-group">
                                            <label for="input-7">Url</label>
                                            <input type="text" class="form-control" name="url" id="input-7">
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="form-group">
                                            <label for="input-11">Connections</label>
                                            <select class="form-control" name="connection" id="input-11">
                                                <% for (Map.Entry<String, DbProperties> set: codeGenerator.getDbConnection().getListConnection().entrySet()){ %>
                                                <option value="<%=set.getKey()%>"><%=set.getKey()%></option>
                                                <% } %>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="input-12">Project path</label>
                                            <input type="text" class="form-control" name="path" id="input-12" placeholder="Enter an absolute path">
                                        </div>
                                        <div class="form-group">
                                            <label for="input-13">View path</label>
                                            <input type="text" class="form-control" name="viewPath" id="input-13" placeholder="Enter an absolute path">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-light px-5">Générer</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div><!--End Row-->

            <!--start overlay-->
            <div class="overlay toggle-menu"></div>
            <!--end overlay-->
        </div>
        <!-- End container-fluid-->
    </div><!--End content-wrapper-->
    <!--Start Back To Top Button-->
    <a href="javaScript:void();" class="back-to-top"><i class="fa fa-angle-double-up"></i> </a>
    <!--End Back To Top Button-->

    <!--Start footer-->
    <footer class="footer">
        <div class="container">
            <div class="text-center">
                Copyright © 2018 Dashtreme Admin
            </div>
        </div>
    </footer>
    <!--End footer-->

    <!--start color switcher-->
    <div class="right-sidebar">
        <div class="switcher-icon">
            <i class="zmdi zmdi-settings zmdi-hc-spin"></i>
        </div>
        <div class="right-sidebar-content">

            <p class="mb-0">Gaussion Texture</p>
            <hr>

            <ul class="switcher">
                <li id="theme1"></li>
                <li id="theme2"></li>
                <li id="theme3"></li>
                <li id="theme4"></li>
                <li id="theme5"></li>
                <li id="theme6"></li>
            </ul>

            <p class="mb-0">Gradient Background</p>
            <hr>

            <ul class="switcher">
                <li id="theme7"></li>
                <li id="theme8"></li>
                <li id="theme9"></li>
                <li id="theme10"></li>
                <li id="theme11"></li>
                <li id="theme12"></li>
                <li id="theme13"></li>
                <li id="theme14"></li>
                <li id="theme15"></li>
            </ul>

        </div>
    </div>
    <!--end color switcher-->

</div><!--End wrapper-->


<!-- Bootstrap core JavaScript-->
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/popper.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>

<!-- simplebar js -->
<script src="assets/plugins/simplebar/js/simplebar.js"></script>
<!-- sidebar-menu js -->
<script src="assets/js/sidebar-menu.js"></script>

<!-- Custom scripts -->
<script src="assets/js/app-script.js"></script>

</body>
</html>

