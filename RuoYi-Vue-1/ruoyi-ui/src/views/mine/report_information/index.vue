<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="公司ID" prop="companyId">
        <el-input
          v-model="queryParams.companyId"
          placeholder="请输入公司ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="年份" prop="year">
        <el-input
          v-model="queryParams.year"
          placeholder="请输入年份"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="月份" prop="month">
        <el-input
          v-model="queryParams.month"
          placeholder="请输入月份"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="营业收入" prop="revenue">
        <el-input
          v-model="queryParams.revenue"
          placeholder="请输入营业收入"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="净利润" prop="profit">
        <el-input
          v-model="queryParams.profit"
          placeholder="请输入净利润"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['mine:report_information:add']"
        >新增</el-button>
      </el-col>
      <!--      [导入]第一步:添加一个导入的按钮↓-->
      <el-col :span="1.5">
        <!--            v-hasPermi =权限字符 ,在数据库 sys_menu 内补足展示按钮以及权限字符:daoru 即可限制哪些角色有权限使用该按钮-->
        <!--        @click="dianji_daoru" 中的 dianji_daoru 为点击按钮产生的事件 可以自行修改事件名称-->
        <el-button
          v-hasPermi="['mine:employee_information:daoru']"
          icon="el-icon-upload2"
          size="mini"
          type="info"
          @click="dianji_daoru"
        >导入
        </el-button>
      </el-col>
      <!--      [导入]第一步:添加一个导入的按钮↑-->
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['mine:report_information:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['mine:report_information:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['mine:report_information:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="report_informationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="报表ID" align="center" prop="reportId" />
      <el-table-column label="公司ID" align="center" prop="companyId" />
      <el-table-column label="年份" align="center" prop="year" />
      <el-table-column label="月份" align="center" prop="month" />
      <el-table-column label="营业收入" align="center" prop="revenue" />
      <el-table-column label="净利润" align="center" prop="profit" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['mine:report_information:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['mine:report_information:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改月度财报信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="公司ID" prop="companyId">
          <el-input v-model="form.companyId" placeholder="请输入公司ID" />
        </el-form-item>
        <el-form-item label="年份" prop="year">
          <el-input v-model="form.year" placeholder="请输入年份" />
        </el-form-item>
        <el-form-item label="月份" prop="month">
          <el-input v-model="form.month" placeholder="请输入月份" />
        </el-form-item>
        <el-form-item label="营业收入" prop="revenue">
          <el-input v-model="form.revenue" placeholder="请输入营业收入" />
        </el-form-item>
        <el-form-item label="净利润" prop="profit">
          <el-input v-model="form.profit" placeholder="请输入净利润" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listReport_information, getReport_information, delReport_information, addReport_information, updateReport_information } from "@/api/mine/report_information";

export default {
  name: "Report_information",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 月度财报信息表格数据
      report_informationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        companyId: null,
        year: null,
        month: null,
        revenue: null,
        profit: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        companyId: [
          { required: true, message: "公司ID不能为空", trigger: "blur" }
        ],
        year: [
          { required: true, message: "年份不能为空", trigger: "blur" }
        ],
        month: [
          { required: true, message: "月份不能为空", trigger: "blur" }
        ],
        revenue: [
          { required: true, message: "营业收入不能为空", trigger: "blur" }
        ],
        profit: [
          { required: true, message: "净利润不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询月度财报信息列表 */
    getList() {
      this.loading = true;
      listReport_information(this.queryParams).then(response => {
        this.report_informationList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        reportId: null,
        companyId: null,
        year: null,
        month: null,
        revenue: null,
        profit: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.reportId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加月度财报信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const reportId = row.reportId || this.ids
      getReport_information(reportId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改月度财报信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.reportId != null) {
            updateReport_information(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addReport_information(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const reportIds = row.reportId || this.ids;
      this.$modal.confirm('是否确认删除月度财报信息编号为"' + reportIds + '"的数据项？').then(function() {
        return delReport_information(reportIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('mine/report_information/export', {
        ...this.queryParams
      }, `report_information_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
