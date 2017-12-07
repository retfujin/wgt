

// Enter replace Tab get focus
function shiftfocus()   
{   
	var   keychar,fn,fesum,i,j,k,fsum;   
	var   fname=event.srcElement.name;   
	if(document.layers)   //ns   
	{   
		document.captureEvents(event.keydown);   
		kechar=event.which;   
	}   
	if(document.all)   //IE     
		keychar=event.keyCode;   
	if(keychar==13)   
	{   
		fsum=document.forms.length;   
		if(fname!=null)     
		{   
			for(i=0;i<fsum;i++)   
			{   
				fesum=document.forms[i].elements.length;   
				for(j=0;j<fesum;j++)   
				{   
					if(fname==document.forms[i].elements[j].name)   
					{   
						if(document.forms[i].elements[j].type=="textarea")
						{   
							return ;   
						}
						if(document.forms[i].elements[j].type=="submit"||document.forms[i].elements[j].type=="reset"||document.forms[i].elements[j].type=="button")   
						{   
							//document.forms[i].elements[j].click();   
							return   false;   
						}   
						k=j+1;     
						if(k>=fesum)   
						{   
							i++;   
							k=0;   
						}   
						if(i>=fsum)   
						{   
							i=0;   
							k=0;   
						}   
						for(;document.forms[i].elements[k].type=="hidden";k++);   
							document.forms[i].elements[k].focus();  
						 
						if(document.forms[i].elements[k].type=="submit"||document.forms[i].elements[k].type=="reset"||document.forms[i].elements[k].type=="button")   
						{   
						//	document.forms[i].elements[k].click();   
							
							return false;   
						}   
						
						//自己添加 对submit的 confirm
						if(document.forms[i].elements[k].type=="submit")   
						{   
							if(!confirm("确定要提交吗"))
								return false; 
							document.forms[i].elements[k].click();   
						}   

						return false;   
					}//if   
				}//for   
			}//for   
		}//if   
		document.forms[0].elements[0].onfocus().select();  
		return   false;   
	}//if   
}//function     
document.onkeydown=shiftfocus; 

