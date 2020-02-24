/**
 *  RepDev - RepGen IDE for Symitar
 *  Copyright (C) 2007  Jake Poznanski, Ryan Schultz, Sean Delaney
 *  http://repdev.org/ <support@repdev.org>
 *
 *  This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.repdev;

import java.util.*;

import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;
import org.swtcompare.*;

import com.repdev.parser.*;

/**
 * Extends the basic compare composite to add repdev specific features
 * @author poznanja
 *
 */
public class CompareComposite extends BasicCompareComposite implements TabView {
	private SymitarFile leftFile, rightFile;
	private SyntaxHighlighter leftHighlighter, rightHighlighter;
	private RepgenParser leftParser, rightParser;
	private int[] lIntLines, rIntLines; //Lines needing background highlighting
	private boolean changed = false;
	
	public CompareComposite(Composite parent, CTabItem tabItem, SymitarFile leftFile, SymitarFile rightFile, Color bgcolor){
		super(parent,leftFile.getName(), rightFile.getName(),RepDevMain.mainShell.getFileImage(leftFile),RepDevMain.mainShell.getFileImage(rightFile));
		
		this.setLeftFile(leftFile);
		this.setRightFile(rightFile);
		
		//Setup custom stuff on the styled texts for our application
		String sData = leftFile.getData();
		
		if( sData == null )
			return;
		
		leftTxt.setText(sData);
		
		if( leftFile.getType() == FileType.REPGEN)
		{
			leftParser = new RepgenParser(leftTxt, leftFile);
			//leftHighlighter = new SyntaxHighlighter(leftParser, BasicCompareComposite.boxFill,lIntLines);
			leftHighlighter = new SyntaxHighlighter(leftParser, bgcolor,lIntLines);
			leftParser.reparseAll();
		}
		
		sData = rightFile.getData();
		
		if( sData == null)
			return;
			
		rightTxt.setText(sData);
		
		if( rightFile.getType() == FileType.REPGEN)
		{
			rightParser = new RepgenParser(rightTxt, rightFile);
			//rightHighlighter = new SyntaxHighlighter(rightParser, BasicCompareComposite.boxFill,rIntLines);
			rightHighlighter = new SyntaxHighlighter(rightParser, bgcolor,rIntLines);
			rightParser.reparseAll();
		}
		
		refreshDiffs();
		
		if( !changed ){
			MessageBox mb = new MessageBox(this.getShell(),SWT.OK | SWT.ICON_INFORMATION);
			mb.setMessage("There are no differences between these two files!");
			mb.setText("No Differences");
			mb.open();
		}
	}
	
	@Override
	/**
	 * Allows for custom highlighting
	 */
	public void redraw() {
		super.redraw();
		leftTxt.redrawRange(0, leftTxt.getCharCount(), true);
		rightTxt.redrawRange(0, rightTxt.getCharCount(), true);
	}

	@Override
	protected void refreshDiffs() {
		super.refreshDiffs();
		
		//Create custom diffs line
		ArrayList<Integer> lLines = new ArrayList<Integer>(), rLines = new ArrayList<Integer>();
		
		System.out.println(Arrays.asList(diffs));
		
		for( RangeDifference diff : diffs){
			if( diff.kind() != RangeDifference.NOCHANGE ){
				changed = true;
				
				for( int i = diff.leftStart(); i < diff.leftEnd(); i++)
					lLines.add(i);
				
				for( int i = diff.rightStart(); i < diff.rightEnd(); i++)
					rLines.add(i);
			}
		}
		
		lIntLines = new int[lLines.size()];
		rIntLines = new int[rLines.size()];
		
		int count = 0;
		
		for( int i : lLines )
		{
			lIntLines[count] = i;
			count++;
		}
		
		count = 0;
		
		for( int i : rLines )
		{
			rIntLines[count] = i;
			count++;
		}
		
		if( leftHighlighter != null)
			leftHighlighter.setCustomLines(lIntLines);
		if( rightHighlighter != null)
			rightHighlighter.setCustomLines(rIntLines);
		
	}

	public SymitarFile getLeftFile() {
		return leftFile;
	}

	public void setLeftFile(SymitarFile leftFile) {
		this.leftFile = leftFile;
	}

	public SymitarFile getRightFile() {
		return rightFile;
	}

	public void setRightFile(SymitarFile rightFile) {
		this.rightFile = rightFile;
	}

}
