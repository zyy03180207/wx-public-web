function getCookie(c_name)
{
    if (document.cookie.length>0)
    {
		    c_start=document.cookie.indexOf(c_name + "=");
		    if (c_start!=-1)
		    { 
			    c_start=c_start + c_name.length+1;
			    c_end=document.cookie.indexOf(";",c_start);
			    if (c_end==-1)
			    {
				    c_end=document.cookie.length;
			    }
			    return decodeURIComponent(myreplace_head(document.cookie.substring(c_start,c_end),'+',' '));
		    }
	}
	return null;
}
function setCookie( name, value, expires, path, domain, secure ) {
	var today = new Date();
	today.setTime( today.getTime() );
	if ( !expires ) {
		expires = 365 * 1000 * 60 * 60 * 24;
	}
	else
	{
	    expires = expires * 1000 * 60 * 60 * 24;
	}
	var expires_date = new Date( today.getTime() + (expires) );
	document.cookie = name+'='+encodeURI( value ) +                     
		( ( expires ) ? ';expires='+expires_date.toGMTString() : '' ) + 
		( ( path ) ? ';path=' + path : '' ) +
		( ( domain ) ? ';domain=' + domain : '' ) +
		( ( secure ) ? ';secure' : '' );
}

function insertCookie(name,value)
{
   if(!value)
   {
        return;
   }
   var values = getCookie(name);
   if(values)
   {
        values = values.replace(value+"|",'');
   }
   if(values)
   {
        
        setCookie(name,value+"|"+values);
   }
   else
   { 
        setCookie(name,value+"|");
   }
}

function deleteCookie( name, path, domain ) {
	if ( getCookie( name ) ) document.cookie = name + '=' +
			( ( path ) ? ';path=' + path : '') +
			( ( domain ) ? ';domain=' + domain : '' ) +
			';expires=Thu, 01-Jan-1970 00:00:01 GMT';
}

function myreplace_head(str,from,to)
{
	while(str.indexOf(from)	!=	-1)
	{
		str	=	str.replace(from,to);
	}
	return str;
}