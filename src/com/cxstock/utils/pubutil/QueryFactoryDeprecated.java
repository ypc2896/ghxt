package com.cxstock.utils.pubutil;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.FastArrayList;
import org.hibernate.type.Type;

import com.lbs.commons.GlobalNames;
import com.lbs.commons.IPublished;
import com.lbs.leaf.exception.LeafException;
import com.lbs.leaf.util.DateUtil;
import com.lbs.leaf.util.StringHelper;

public class QueryFactoryDeprecated
  implements IPublished
{
  static final String baseExp = "\\s*(([L,l][I,i][K,k][E,e])|=|(>)|(<)|(>=)|(<=)|(!=))\\s*:";
  static final String baseAnd = "\\s*(([A,a][N,n][D,d])|([O,o][R,r]))?\\s*";

  public static synchronized String getHQL(String paramString, String[] paramArrayOfString, Type[] paramArrayOfType, Object paramObject)
    throws Exception
  {
    if (paramArrayOfString.length != paramArrayOfType.length)
      throw new Exception("参数名称数组的长度与参数类型数组的长度不匹配！");
    paramString = StringHelper.dealOrderBy(paramString);
    String str1 = "";
    if (paramString.indexOf(GlobalNames.ORDER_BY) > -1)
    {
      str1 ="order by " + getHQL(paramString.substring(paramString.lastIndexOf(GlobalNames.ORDER_BY) + 8),paramArrayOfString,paramArrayOfType,paramObject);
      paramString = paramString.substring(0, paramString.lastIndexOf(GlobalNames.ORDER_BY));
      
    }
    String[] arrayOfString1 = a(paramObject, paramArrayOfString);
    Type[] arrayOfType = a(arrayOfString1, paramArrayOfString, paramArrayOfType);
    String str2 = a(paramString, paramObject, paramArrayOfString);
    String[] arrayOfString2 = a(str2, paramObject, arrayOfString1, arrayOfType);
    String str3 = a(str2, arrayOfString1, arrayOfString2);
    return str3 + " " + str1;
  }

  private static String[] a(Object paramObject, String[] paramArrayOfString)
    throws LeafException
  {
    FastArrayList localFastArrayList = new FastArrayList();
    if (paramArrayOfString == null)
      return null;
    for (int i = 0; i < paramArrayOfString.length; i++)
    {
      String str = paramArrayOfString[i];
      if ("".equals(a(paramObject, str, i)))
        continue;
      localFastArrayList.add(str);
    }
    String[] arrayOfString = new String[localFastArrayList.size()];
    localFastArrayList.toArray(arrayOfString);
    return arrayOfString;
  }

  private static Type[] a(String[] paramArrayOfString1, String[] paramArrayOfString2, Type[] paramArrayOfType)
    throws LeafException
  {
    FastArrayList localFastArrayList = new FastArrayList();
    if (paramArrayOfString2 == null)
      return null;
    for (int i = 0; i < paramArrayOfString1.length; i++)
    {
      String str = paramArrayOfString1[i];
      for (int j = 0; j < paramArrayOfString2.length; j++)
      {
        if (!str.equals(paramArrayOfString2[j]))
          continue;
        localFastArrayList.add(paramArrayOfType[j]);
        break;
      }
    }
    Type[] arrayOfType = new Type[localFastArrayList.size()];
    localFastArrayList.toArray(arrayOfType);
    return arrayOfType;
  }
  
  private static String[] a(String paramString, Object paramObject, String[] paramArrayOfString, Type[] paramArrayOfType)
    throws Exception
  {
    FastArrayList localFastArrayList = new FastArrayList();
    if (paramArrayOfString == null)
      return null;
    for (int i = 0; i < paramArrayOfString.length; i++)
    {
      String str1 = paramArrayOfString[i];
      Type localType = paramArrayOfType[i];
      Object localObject = a(paramObject, str1, i);
      StringBuffer localStringBuffer;
      if (localType.getName().equals("string"))
      {
        localStringBuffer = new StringBuffer((String)localObject);
        String str2 = "like\\s*:" + str1 + "(\\s*[+]+\\s*['%']+)+";
        String str3 = "like\\s*(['%']+\\s*[+]+\\s*)+:" + str1;
        String str4 = "like\\s*:" + str1;
        if (StringHelper.exist(str2, paramString))
        {
          localStringBuffer.insert(0, "'").append("%'");
          localFastArrayList.add(localStringBuffer.toString());
        }
        else if (StringHelper.exist(str3, paramString))
        {
          localStringBuffer.insert(0, "'%").append("'");
          localFastArrayList.add(localStringBuffer.toString());
        }
        else if (StringHelper.exist(str4, paramString))
        {
          if (StringHelper.exist("%", localStringBuffer.toString()))
          {
            localStringBuffer.insert(0, "'").append("'");
            localFastArrayList.add(localStringBuffer.toString());
          }
          else
          {
            localStringBuffer.insert(0, "'%").append("%'");
            localFastArrayList.add(localStringBuffer.toString());
          }
        }
        else
        {
          localStringBuffer.insert(0, "'").append("'");
          localFastArrayList.add(localStringBuffer.toString());
        }
      }
      else if (localType.getName().equals("date"))
      {
        localStringBuffer = new StringBuffer("to_date('");
        localStringBuffer.append(DateUtil.dateToString(new java.util.Date(((java.sql.Date)localObject).getTime()), "YYYY-MM-DD"));
        localStringBuffer.append("','YYYY-MM-DD')");
        localFastArrayList.add(i, localStringBuffer.toString());
      }
      else
      {
        localFastArrayList.add(localObject.toString());
      }
    }
    String[] arrayOfString = new String[localFastArrayList.size()];
    localFastArrayList.toArray(arrayOfString);
    return arrayOfString;
  }

  private static String a(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2)
    throws Exception
  {
    String str1 = null;
    String str2 = null;
    String str3 = paramString;
    for (int i = 0; i < paramArrayOfString1.length; i++)
    {
      str1 = paramArrayOfString1[i];
      str2 = paramArrayOfString2[i];
      try
      {
        str3 = StringHelper.regexReplace("(['%']+\\s*[+]+\\s*)?:" + str1 + "(\\s*[+]+\\s*['%']+)?", str2, str3);
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        throw new Exception(localException);
      }
    }
    return str3;
  }

  private static String a(String paramString, Object paramObject, String[] paramArrayOfString)
    throws LeafException
  {
    String str1 = paramString;
    if (paramArrayOfString == null)
      return paramString;
    for (int i = 0; i < paramArrayOfString.length; i++)
    {
      String str2 = paramArrayOfString[i];
      String str3 = "[(]?([a-zA-Z]+[[_]*[a-zA-Z0-9]*]*)[.]?" + "([a-zA-Z]+[[_]*[a-zA-Z0-9]*]*)" + "\\s*(([L,l][I,i][K,k][E,e])|=|(>)|(<)|(>=)|(<=)|(!=))\\s*:" + str2 + "[)]?" + "\\s*(([A,a][N,n][D,d])|([O,o][R,r]))?\\s*";
      if (!"".equals(a(paramObject, str2, i)))
        continue;
      str1 = StringHelper.regexReplace(str3, "", str1);
    }
    str1 = str1.replaceFirst("(\\s+(([W,w][H,h][E,e][R,r][E,e])|([A,a][N,n][D,d])|([O,o][R,r]))\\s*)+\\z", "");
    return str1;
  }

  private static Object a(Object paramObject, String paramString, int paramInt)
  {
    Object localObject = null;
    try
    {
      if ((paramObject instanceof Object[]))
        localObject = ((Object[])paramObject)[paramInt];
      else
        localObject = PropertyUtils.getProperty(paramObject, paramString);
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      throw new LeafException(localNoSuchMethodException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new LeafException(localInvocationTargetException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new LeafException(localIllegalAccessException);
    }
    if (localObject == null)
      localObject = "";
    return localObject;
  }
}