<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lan="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>Refer & Earn</title>

<!-- Files to be loaded -->
<script type="text/javascript"
	src="<c:url value="/resources/jquery-1.11.0.min.js" />"></script>
<!-- Include CSS files -->
<%@ include file="jsp-includes/css_files.jsp"%>

<!-- Include JS files -->
<%@ include file="jsp-includes/js_files.jsp"%>

<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.5";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>
<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+'://platform.twitter.com/widgets.js';fjs.parentNode.insertBefore(js,fjs);}}(document, 'script', 'twitter-wjs');</script>

<script src="//platform.linkedin.com/in.js" type="text/javascript"> lang: en_US</script>

<!-- Place this tag after the last share tag. -->
<script type="text/javascript">
 (function() {
   var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
   po.src = 'https://apis.google.com/js/platform.js';
   var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
 })();
</script>

</head>
<body>

	<!-- Include top navigation -->
	<%@include file="jsp-includes/top_navigation.jsp"%>
	
	<div id="container">
		<div id="sidebar" class="sidebar-fixed">
			<div id="sidebar-content">

				<!-- Left Navigation -->
				<%@include file="jsp-includes/left_navigation.jsp"%>

			</div>
		<div id="divider" class="resizeable"></div>

	</div>
		<!-- /Sidebar -->
		
		<div id="content">
			<div class="container">
				
				<!--=== Page Header ===-->
						<div class="page-header">
		
							<div class="page-title">
								<h3>Refer & Earn</h3>
								<span>share your experiences!</span>
							</div>
						</div>
							
							<div class="panel-group">
					  		  <div class="panel panel-default">
					  		  
					    		  <div class="panel-heading">
						    		  <div class="col-md-12 col-md-offset-4">
										<img src="<c:url value="/resources/icons_webapp/logo_crop.png" />"/>
									  </div>
					    		  </div>
					     		 	<div class="panel-body">
										<div class="col-md-3 col-md-offset-5">
												<button class="btn btn-success"><c:out value="${referralCode.code}"></c:out></button>
										</div>
									</div>
<%-- 									<div class="panel-body">
										<div class="col-md-12">	
											<c:out value="${referralCode.referral_message}"></c:out>
										</div>
									</div> --%>
									<div class="panel-body">
										<div class="col-md-12">	
											<c:out value="${referralCode.referral_scheme_desc}"></c:out>
										</div>
									</div>
									<div class="panel-body">
							
										<div class="col-md-12">  
                                            <a href="https://www.facebook.com/dialog/feed?app_id=1155616421123516&name=EasyCommute-Affordable Bus Shuttle Service!&link=http://www.easycommute.co/
                                            &description=<c:out value="${referralCode.referral_message}"/>&display=popup&redirect_uri=http://www.easycommute.co/
                                            &picture=http://dpjoyft3yy4j8.cloudfront.net/webapp/resources/icons_webapp/logo_crop.png" 
                                            class="btn btn-success btn-block">Share Easy Commute App</a>
                                        </div>
										
									</div>
						   	 
							
<!-- 									<div class="panel-body"> -->
<!-- 										<div class="col-md-3">	 -->
<!-- 											<div class="fb-like" data-href="https://www.facebook.com/EasyCommute" data-layout="standard" data-action="like" data-show-faces="true" data-share="true"></div> -->
<!-- 										</div> -->
<!-- 										<br> -->
<!-- 										<div class="col-md-3"> -->
<!-- 											<div id="fb-root"></div> -->
<!-- 											<div class="fb-share-button" data-href="https://play.google.com/store/apps/details?id=easy.commute.shuttle.app" data-layout="button"></div> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 									<div class="panel-body"> -->
<!-- 										<div class="col-md-3">	 -->
<!-- 											<a href="https://twitter.com/EasyCommute" class="twitter-follow-button" data-show-count="false" data-size="large" data-dnt="true">Follow @EasyCommute</a> -->
<!-- 										</div> -->
<!-- 										<br>		 -->
<!-- 										<div> -->
<!-- 											<a href="https://twitter.com/share" class="twitter-share-button" data-via="EasyCommute" data-size="large" data-related="EasyCommute" data-hashtags="EasyCommute" data-dnt="true">Tweet</a> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 									<div class="panel-body"> -->
<!-- 										<div class="col-md-3">	 -->
<!-- 											<div class="g-plus" data-action="share" data-annotation="none" data-height="24" data-href="https://play.google.com/store/apps/details?id=easy.commute.shuttle.app"></div> -->
<!-- 										</div> -->
<!-- 										<br>		 -->
<!-- 										<div> -->
<!-- 											<div class="g-follow" data-annotation="none" data-height="24" data-href="https://plus.google.com/101399137308735269622" data-rel="publisher"></div> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 									<div class="panel-body"> -->
<!-- 										<div class="col-md-3">	 -->
<!-- 											<a href="https://www.linkedin.com/company/10250548" data-type="linkedin" data-url="https://play.google.com/store/apps/details?id=easy.commute.shuttle.app" class="ysshare socialIconWrapper li" data-title="EasyCommute" data-via="YourStoryCo"> -->
<!-- 											 <i class="icon-linkedin"></i> -->
<!-- 											 </a> -->
<!-- 										</div> -->
<!-- 									</div> -->
									<!-- Top Navigation Bar -->
									<div class="panel-body">
                                        <div class="col-md-6">  
                                            <div class="fb-like" data-href="https://www.facebook.com/EasyCommute" data-layout="standard" data-action="like" data-show-faces="true" data-share="true"></div>
                                            <div id="fb-root"></div>
<!--                                             <div class="fb-share-button" data-href="https://play.google.com/store/apps/details?id=easy.commute.shuttle.app" data-layout="button"></div> -->
                                        </div>
                                        <div class="col-md-6">  
                                            <a href="https://twitter.com/EasyCommute" class="twitter-follow-button" data-show-count="false" data-size="medium" data-dnt="true">Follow @EasyCommute</a> &nbsp;&nbsp;
                                            <a href="https://twitter.com/share" class="twitter-share-button" data-via="EasyCommute" data-size="medium" data-related="EasyCommute" data-hashtags="EasyCommute" data-dnt="true">Tweet</a>
                                        </div>
                                    </div>
                                    
                                    <div class="panel-body">
                                        <div class="col-md-6">  
                                            <div class="g-plus" data-action="share" data-annotation="none" data-height="24" data-href="https://play.google.com/store/apps/details?id=easy.commute.shuttle.app"></div> &nbsp;&nbsp;
                                            <div class="g-follow" data-annotation="none" data-height="24" data-href="https://plus.google.com/101399137308735269622" data-rel="publisher"></div> &nbsp;&nbsp;
                                             
                                             <a href="https://www.linkedin.com/company/10250548" data-type="linkedin" data-url="https://play.google.com/store/apps/details?id=easy.commute.shuttle.app" class="ysshare socialIconWrapper li" data-title="EasyCommute" data-via="YourStoryCo">
                                               <i class="icon-linkedin"></i>
                                             </a>
                                        </div>
                                    </div>
						
						   </div>
						
						
						</div>
							
						</div>
						</div>	
						</div>				
</body>
</html>
