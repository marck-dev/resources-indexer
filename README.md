# ResourcesIndexer
<i>
Author: <strong>Marck C. Guzm&aacute;n</strong><br/>
Email: <a href="mailto:marck.guzman.dev@gmail.com">marck.guzman.dev@gmail.com</a>
</i><br/><br/><br/>
Generate a java file with all directories and files.<br/>
If the dir tree is:<br/><pre>
resources_folder/
&nbsp;&nbsp;&nbsp;&#4523;&nbsp; xml/
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&#4449; folders.xml
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&#4523; img.xml
</pre>
The output java will be:
<pre>
public class R{
&nbsp;&nbsp;&nbsp;&nbsp;public static final class xml{
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;public static final String folder = "resources_folder/xml/folders.xml";
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;public static final String img = "resources_folder/xml/img.xml";
&nbsp;&nbsp;&nbsp;&nbsp;}
}
</pre>
<br/><br/>
<h2>Argumenst</h2>
<br/>
<ul>
<li><strong>--package-name | -p </strong>&nbsp;&nbsp;&nbsp;&nbsp;set the name of the package, remenber that package = folders tree.</li>
<li><strong>--class-name | -c </strong>&nbsp;&nbsp;&nbsp;&nbsp;set the class and file name.</li>
<li><strong>--resources-dir | -d </strong>&nbsp;&nbsp;&nbsp;&nbsp;set the resources folder to indexer.</li>
</ul>