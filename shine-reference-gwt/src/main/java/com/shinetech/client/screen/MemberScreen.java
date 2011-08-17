/*
 * Copyright 2008 Shine Technologies Licensed under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for
 * the specific language governing permissions and limitations under the
 * License.
 */
package com.shinetech.client.screen;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.shinetech.sample.common.dto.MemberDTO;

/*
 * @author Stephen Callaghan
 */
public class MemberScreen extends BaseScreen implements ClickListener
{

    // Helper identifiers for service calls
    private final static int SVC_MEMBER_SAVE = 1;
    private final static int SVC_MEMBER_DELETE = 2;
    private final static int SVC_MEMBER_COUNT = 3;
    private final static int SVC_MEMBER_GET = 4;

    private Button btnSearch = new Button("Search");
    private Button btnDelete = new Button("Delete");
    private Button btnSave = new Button("Save");
    private Button btnRefresh = new Button("Refresh");

    private long memberId;
    private TextBox tbSearch = new TextBox();
    private TextBox tbFirstName = new TextBox();
    private TextBox tbLastName = new TextBox();
    private TextArea tbStatusBox = new TextArea();


    public MemberScreen()
    {
        init();
        tbFirstName.setName("firstName");
        GWT.log("Name : " + tbFirstName.getName(), null);
    }


    private void init()
    {
        add(new HTML("<h1>Shine Reference GWT</h1>"));
        add(new HTML("<p>This sample shows simple member search and edit functionality</p><br>"));

        // Search Panel
        HorizontalPanel pnlSearch = new HorizontalPanel();
        pnlSearch.add(new Label("Search for Member ID : "));
        pnlSearch.add(tbSearch);
        pnlSearch.add(btnSearch);
        btnSearch.addClickListener(this);
        add(pnlSearch);

        // Single Member Details Panel
        add(new HTML("<br>"));
        add(new Label("Member Details : "));
        FlexTable tblMember = new FlexTable();
        tblMember.setText(0, 0, "First Name");
        tblMember.setWidget(0, 1, tbFirstName);
        tblMember.setText(1, 0, "Last Name");
        tblMember.setWidget(1, 1, tbLastName);
        add(tblMember);

        // Button Panel
        HorizontalPanel btnPanel = new HorizontalPanel();
        btnDelete.addClickListener(this);
        btnSave.addClickListener(this);
        btnRefresh.addClickListener(this);
        btnPanel.add(btnDelete);
        btnPanel.add(btnSave);
        btnPanel.add(btnRefresh);
        add(btnPanel);

        // Status Box
        add(new HTML("<br>"));
        add(new Label("Status : "));
        tbStatusBox.setCharacterWidth(80);
        tbStatusBox.setVisibleLines(4);
        add(tbStatusBox);

        // Set Focus for Screen
        focusWidget = tbSearch;
    }


    public void setMember(MemberDTO member)
    {
        if (member == null)
        {
            member = new MemberDTO();
        }
        memberId = member.getId();
        tbFirstName.setText(member.getFirstName());
        tbLastName.setText(member.getLastName());
    }

    
    /**
     * Return current Member DTO 
     */
    private MemberDTO getMemberDTO()
    {
        MemberDTO member = new MemberDTO();
        member.setId(memberId);
        member.setFirstName(tbFirstName.getText());
        member.setLastName(tbLastName.getText());
        return member;
    }

    public void onClick(Widget sender)
    {
        if (sender == btnSearch)
        {
            callMemberDetailsService(SVC_MEMBER_GET);
        }
        else if (sender == btnDelete)
        {
            callMemberDetailsService(SVC_MEMBER_DELETE);
        }
        else if (sender == btnRefresh)
        {
            callMemberDetailsService(SVC_MEMBER_COUNT);
        }
        else if (sender == btnSave)
        {
            callMemberDetailsService(SVC_MEMBER_SAVE);
        }
    }


    /**
     * Member Object has been returned from service 
     */
    private void processMemberGet(Object result)
    {
        MemberDTO member = (MemberDTO)result;
        if (member == null)
        {
            memberId = 0;
            tbStatusBox.setText("Not Found");
            tbSearch.setFocus(true);
        }
        else
        {
            setMember(member);
        }
    }


    /**
     * Member has been saved
     * @param result
     */
    private void processMemberSave(Object result)
    {
        tbStatusBox.setText("Member Saved : ");
    }


    /**
     * Number of members has been returned 
     */
    private void processMemberCount(Object result)
    {
        tbStatusBox.setText("Number of Members : " + result);
    }


    /** 
     * Member has been deleted
     */
    private void processMemberDelete(Object result)
    {
        tbStatusBox.setText("Member Deleted");
        setMember(null);
    }


    /**
     * Async Call to Service
     */
    private void callMemberDetailsService(final int serviceCall)
    {
        GWT.log("callMemberDetailsService() : " + serviceCall, null);
        AsyncCallback callback = new AsyncCallback()
        {
            public void onSuccess(Object result)
            {
                try
                {
                    GWT.log("Service returned, display result", null);
                    switch (serviceCall)
                    {
                        case SVC_MEMBER_SAVE:
                            processMemberSave(result);
                            break;
                        case SVC_MEMBER_DELETE:
                            processMemberDelete(result);
                            break;
                        case SVC_MEMBER_COUNT:
                            processMemberCount(result);
                            break;
                        case SVC_MEMBER_GET:
                            processMemberGet(result);
                            break;
                    }

                }
                catch (Exception e)
                {
                    tbStatusBox.setText(e.toString());
                    GWT.log("Exception calling service", e);
                }
            }


            public void onFailure(Throwable caught)
            {
                tbStatusBox.setText(caught.toString());
                GWT.log("Exception calling service", caught);
            }
        };

        // Call Service
        tbStatusBox.setText("");
        try
        {
            switch (serviceCall)
            {
                case SVC_MEMBER_SAVE:
                    svcMemberDetailsAsync.saveMemberDetails(getMemberDTO(), callback);
                    break;
                case SVC_MEMBER_DELETE:
                    svcMemberDetailsAsync.deleteMemberDetails(memberId, callback);
                    break;
                case SVC_MEMBER_COUNT:
                    tbSearch.setText("");
                    setMember(null);
                    svcMemberDetailsAsync.countAll(callback);
                    break;
                case SVC_MEMBER_GET:
                    long id = Long.parseLong(tbSearch.getText());
                    svcMemberDetailsAsync.getMemberDetails(id, callback);
                    break;
            }

        }
        catch (Exception e)
        {
            tbStatusBox.setText(e.toString());
        }
    }
}
