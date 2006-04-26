/**********************************************************************************
 * $URL$
 * $Id$
 ***********************************************************************************
 *
 * Copyright (c) 2003, 2004, 2005, 2006 The Sakai Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 * 
 *      http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 *
 **********************************************************************************/

package org.sakaiproject.announcement.api;

import java.util.Collection;
import java.util.List;

import org.sakaiproject.entity.api.Entity;
import org.sakaiproject.time.api.Time;

/**
 * <p>
 * Assignment is an interface for the Sakai assignments module. It represents a specific assignment (as for a specific section or class).
 * </p>
 */
public interface Assignment extends Entity, Comparable
{
	/** Grade type not set */
	public static final int GRADE_TYPE_NOT_SET = -1;

	/** Ungraded grade type */
	public static final int UNGRADED_GRADE_TYPE = 1;

	/** Letter grade type */
	public static final int LETTER_GRADE_TYPE = 2;

	/** Score based grade type */
	public static final int SCORE_GRADE_TYPE = 3;

	/** Pass/fail grade type */
	public static final int PASS_FAIL_GRADE_TYPE = 4;

	/** Grade type that only requires a check */
	public static final int CHECK_GRADE_TYPE = 5;

	/** Ungraded grade type string */
	public static final String UNGRADED_GRADE_TYPE_STRING = "Ungraded";

	/** Letter grade type string */
	public static final String LETTER_GRADE_TYPE_STRING = "Letter Grade";

	/** Score based grade type string */
	public static final String SCORE_GRADE_TYPE_STRING = "Points";

	/** Pass/fail grade type string */
	public static final String PASS_FAIL_GRADE_TYPE_STRING = "Pass/Fail";

	/** Grade type that only requires a check string */
	public static final String CHECK_GRADE_TYPE_STRING = "Checkmark";

	/** Assignment type not yet set */
	public static final int ASSIGNMENT_SUBMISSION_TYPE_NOT_SET = -1;

	/** Text only assignment type */
	public static final int TEXT_ONLY_ASSIGNMENT_SUBMISSION = 1;

	/** Attachment only assignment type */
	public static final int ATTACHMENT_ONLY_ASSIGNMENT_SUBMISSION = 2;

	/** Text and/or attachment assignment type */
	public static final int TEXT_AND_ATTACHMENT_ASSIGNMENT_SUBMISSION = 3;

	/** Honor Pledge not yet set */
	public static final int HONOR_PLEDGE_NOT_SET = -1;

	/** Honor Pledge not yet set */
	public static final int HONOR_PLEDGE_NONE = 1;

	/** Honor Pledge not yet set */
	public static final int HONOR_PLEDGE_ENGINEERING = 2;

	/**
	 * Access the AssignmentContent of this Assignment.
	 * 
	 * @return The Assignment's AssignmentContent.
	 */
	public AssignmentContent getContent();

	/**
	 * Access the reference of the AssignmentContent of this Assignment.
	 * 
	 * @return The AssignmentContent's reference.
	 */
	public String getContentReference();

	/**
	 * Access the first time at which the assignment can be viewed; may be null.
	 * 
	 * @return The Time at which the assignment is due, or null if unspecified.
	 */
	public Time getOpenTime();

	/**
	 * Access the time at which the assignment is due; may be null.
	 * 
	 * @return The Time at which the Assignment is due, or null if unspecified.
	 */
	public Time getDueTime();

	/**
	 * Access the drop dead time after which responses to this assignment are considered late; may be null.
	 * 
	 * @return The Time object representing the drop dead time, or null if unspecified.
	 */
	public Time getDropDeadTime();

	/**
	 * Access the close time after which this assignment can no longer be viewed, and after which submissions will not be accepted. May be null.
	 * 
	 * @return The Time after which the Assignment is closed, or null if unspecified.
	 */
	public Time getCloseTime();

	/**
	 * Access the section info.
	 * 
	 * @return The section id.
	 */
	public String getSection();

	/**
	 * Access the context at the time of creation.
	 * 
	 * @return String - the context string.
	 */
	public String getContext();

	/**
	 * Get whether this is a draft or final copy.
	 * 
	 * @return True if this is a draft, false if it is a final copy.
	 */
	public boolean getDraft();

	/**
	 * Access the creator of this object.
	 * 
	 * @return String - The id of the creator.
	 */
	public String getCreator();

	/**
	 * Access the time that this object was created.
	 * 
	 * @return The Time object representing the time of creation.
	 */
	public Time getTimeCreated();

	/**
	 * Access the list of authors.
	 * 
	 * @return List of authors as User objects.
	 */
	public List getAuthors();

	/**
	 * Access the time of last modificaiton.
	 * 
	 * @return The Time of last modification.
	 */
	public Time getTimeLastModified();

	/**
	 * Access the author of last modification
	 * 
	 * @return String - The id of the author.
	 */
	public String getAuthorLastModified();

	/**
	 * Access the title.
	 * 
	 * @return The Assignment's title.
	 */
	public String getTitle();

	/**
	 * Access the groups defined for this assignment.
	 * 
	 * @return A Collection (String) of group refs (authorization group ids) defined for this message; empty if none are defined.
	 */
	Collection getGroups();

	/**
	 * Access the access mode for the assignment - how we compute who has access to the assignment.
	 * 
	 * @return The AssignmentAccess access mode for the Assignment.
	 */
	AssignmentAccess getAccess();

	/**
	 * <p>
	 * AssignmentAccess enumerates different access modes for the assignment: site-wide or grouped.
	 * </p>
	 */
	public class AssignmentAccess
	{
		private final String m_id;

		private AssignmentAccess(String id)
		{
			m_id = id;
		}

		public String toString()
		{
			return m_id;
		}

		static public AssignmentAccess fromString(String access)
		{
			if (SITE.m_id.equals(access)) return SITE;
			if (GROUPED.m_id.equals(access)) return GROUPED;
			return null;
		}

		/** channel (site) level access to the message */
		public static final AssignmentAccess SITE = new AssignmentAccess("site");

		/** grouped access; only members of the getGroup() groups (authorization groups) have access */
		public static final AssignmentAccess GROUPED = new AssignmentAccess("grouped");
	}
}
